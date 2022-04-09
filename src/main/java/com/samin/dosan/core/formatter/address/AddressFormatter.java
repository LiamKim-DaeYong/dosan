package com.samin.dosan.core.formatter.address;

import com.samin.dosan.core.code.AddressType;
import com.samin.dosan.core.domain.Address;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AddressFormatter implements Formatter<Address> {
    private AddressType type = AddressType.FULL;

    public void setType(AddressType addressType) {
        this.type = addressType;
    }

    @Override
    public Address parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            String[] splits = text.split(",");
            return new Address(splits[0], splits[1], splits[2]);
        }

        return null;
    }

    @Override
    public String print(Address address, Locale locale) {
        if (address != null) {
            switch (type) {
                case FULL:
                    return String.format("[%s] %s %s", address.getZipCode(),
                            address.getRoadAddress(), address.getDetailAddress());
                case REGION:
                    if (!address.getRoadAddress().isEmpty()) {
                        String[] splits = address.getRoadAddress().split(" ");
                        return String.format("%s %s", splits[0], splits[1]);
                    } else {
                        return "";
                    }
            }
        }

        return null;
    }
}
