package com.example.safway.repository;

import com.example.safway.dto.AddressDetails;

import java.util.ArrayList;
import java.util.List;

public class AddressDetailsRepository {

     private  AddressDetailsRepository(){}

    public static List<AddressDetails> getAllAddresses() {
        List<AddressDetails> addresses = new ArrayList<>();

        addresses.add(new AddressDetails("435 ABD", "Alpha Street", "City A"));
        addresses.add(new AddressDetails("456 VTG", "Beta Street", "City B"));
        addresses.add(new AddressDetails("985 TYK", "Gamma Lane", "City C"));
        addresses.add(new AddressDetails("111 AAA", "Delta Road", "City D"));
        addresses.add(new AddressDetails("222 BBB", "Epsilon Street", "City E"));
        addresses.add(new AddressDetails("333 CCC", "Zeta Avenue", "City F"));
        addresses.add(new AddressDetails("444 DDD", "Eta Street", "City G"));
        addresses.add(new AddressDetails("555 EEE", "Theta Lane", "City H"));
        addresses.add(new AddressDetails("666 FFF", "Iota Road", "City I"));
        addresses.add(new AddressDetails("777 GGG", "Kappa Street", "City J"));
        addresses.add(new AddressDetails("888 HHH", "Lambda Lane", "City K"));
        addresses.add(new AddressDetails("999 III", "Mu Road", "City L"));
        addresses.add(new AddressDetails("101 JJJ", "Nu Street", "City M"));
        addresses.add(new AddressDetails("202 KKK", "Xi Avenue", "City N"));
        addresses.add(new AddressDetails("303 LLL", "Omicron Lane", "City O"));
        addresses.add(new AddressDetails("404 MMM", "Pi Road", "City P"));
        addresses.add(new AddressDetails("505 NNN", "Rho Street", "City Q"));
        addresses.add(new AddressDetails("606 OOO", "Sigma Lane", "City R"));
        addresses.add(new AddressDetails("707 PPP", "Tau Road", "City S"));
        addresses.add(new AddressDetails("808 QQQ", "Upsilon Street", "City T"));
        addresses.add(new AddressDetails("909 RRR", "Phi Lane", "City U"));
        addresses.add(new AddressDetails("121 SSS", "Chi Road", "City V"));
        addresses.add(new AddressDetails("232 TTT", "Psi Street", "City W"));
        addresses.add(new AddressDetails("343 UUU", "Omega Lane", "City X"));
        addresses.add(new AddressDetails("454 VVV", "Alpha2 Road", "City Y"));

        return addresses;
    }
}
