package tech.mohammad.amir.data;

import lombok.Getter;
import tech.mohammad.amir.common.io.JsonFileReader;
import tech.mohammad.amir.dto.GuestDTO;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.reverseOrder;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static tech.mohammad.amir.common.constant.Constants.GUEST_FILE_NAME;
import static tech.mohammad.amir.common.constant.Constants.LOW_PAYER_GUEST_LIMIT;

public class GuestDataProvider {
    private static GuestDataProvider guestDataProvider;
    private List<Integer> guestData;

    @Getter
    private List<GuestDTO> lowPayerGuestList;

    @Getter
    private List<GuestDTO> highPayerGuestList;

    private GuestDataProvider() {
        loadGuestData();
        loadFilteredGuestLists();
    }

    private void loadGuestData() {
        guestData = JsonFileReader.read(GUEST_FILE_NAME);
    }

    private void loadFilteredGuestLists() {
        lowPayerGuestList = getFilteredList(amount -> amount <= LOW_PAYER_GUEST_LIMIT);
        highPayerGuestList = getFilteredList(amount -> amount > LOW_PAYER_GUEST_LIMIT);
    }

    private List<GuestDTO> getFilteredList(Predicate<Integer> filter) {
        return guestData.stream()
                .filter(filter)
                .sorted(reverseOrder())
                .map(GuestDTO::new)
                .collect(toList());
    }

    public static GuestDataProvider getInstance() {
        if(isNull(guestDataProvider)) {
            guestDataProvider = new GuestDataProvider();
        }

        return guestDataProvider;
    }
}
