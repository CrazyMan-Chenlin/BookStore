package business.front.address.service;

import entity.Address;
import exception.MaxAddressException;

import java.util.List;

/**
 * @author chenlin
 */
public interface AddressService {
    void insert(Address address) throws MaxAddressException;
    List<Address> queryAddress(int userId);
    void delete(String id);
    void updateDft(String id);
}
