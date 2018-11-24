package tech.mohammad.amir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.mohammad.amir.data.GuestDataProvider;
import tech.mohammad.amir.dto.GuestDTO;
import tech.mohammad.amir.dto.OccupancyRequestDTO;
import tech.mohammad.amir.dto.OccupancyResponseDTO;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class OccupancyOptimizationService {
    private final GuestDataProvider guestDataProvider = GuestDataProvider.getInstance();

    public OccupancyResponseDTO optimizeOccupancy(OccupancyRequestDTO occupancyRequest) {
        Integer freePremiumRooms = ofNullable(occupancyRequest.getFreePremiumRooms()).orElse(0);
        Integer freeEconomyRooms = ofNullable(occupancyRequest.getFreeEconomyRooms()).orElse(0);

        List<GuestDTO> premiumRoomGuests = new ArrayList<>();
        List<GuestDTO> economyRoomGuests = new ArrayList<>();

        premiumRoomGuests.addAll(assignPremiumRoomToHighPayerGuest(freePremiumRooms));
        freePremiumRooms -= premiumRoomGuests.size();

        int lowPayerToPremiumCount = getLowPayerToPremiumCount(freeEconomyRooms, freePremiumRooms);

        if(lowPayerToPremiumCount > 0) {
            premiumRoomGuests.addAll(assignPremiumRoomToLowPayerGuest(lowPayerToPremiumCount));
        }

        economyRoomGuests.addAll(assignEconomyRoomToLowPayerGuest(freeEconomyRooms, lowPayerToPremiumCount));

        return buildOccupancyResponce(premiumRoomGuests, economyRoomGuests);
    }

    private OccupancyResponseDTO buildOccupancyResponce(List<GuestDTO> premiumRoomGuests, List<GuestDTO> economyRoomGuests) {
        return OccupancyResponseDTO.builder()
                .usageEconomy(economyRoomGuests.size())
                .usagePremium(premiumRoomGuests.size())
                .totalEarningEconomy(getSum(economyRoomGuests))
                .totalEarningPremium(getSum(premiumRoomGuests))
                .build();
    }

    private Integer getSum(List<GuestDTO> guestList) {
        return guestList.stream().mapToInt(GuestDTO::getPriceLimit).sum();
    }

    private List<GuestDTO> assignPremiumRoomToHighPayerGuest(Integer freePremiumRooms) {
        final List<GuestDTO> highPayerGuestList = guestDataProvider.getHighPayerGuestList();

        if(freePremiumRooms >= highPayerGuestList.size()) {
            return highPayerGuestList;
        } else {
            return highPayerGuestList.subList(0, freePremiumRooms);
        }
    }

    private int getLowPayerToPremiumCount(Integer freeEconomyRooms, Integer freePremiumRooms) {
        int lowPayerWithoutRoom = guestDataProvider.getLowPayerGuestList().size() - freeEconomyRooms;
        return min(lowPayerWithoutRoom, freePremiumRooms);
    }

    private List<GuestDTO> assignPremiumRoomToLowPayerGuest(int lowPayerToPremiumCount) {
        return guestDataProvider.getLowPayerGuestList().subList(0, lowPayerToPremiumCount);
    }

    private List<GuestDTO> assignEconomyRoomToLowPayerGuest(Integer freeEconomyRooms, int lowPayerToPremiumCount) {
        final List<GuestDTO> lowPayerGuestList = guestDataProvider.getLowPayerGuestList();

        if(freeEconomyRooms >= lowPayerGuestList.size()) {
            return lowPayerGuestList;
        } else {
            return lowPayerGuestList.subList(lowPayerToPremiumCount, freeEconomyRooms + lowPayerToPremiumCount);
        }
    }
}
