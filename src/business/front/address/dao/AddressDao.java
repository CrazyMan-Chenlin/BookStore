package business.front.address.dao;

import entity.Address;

import java.util.List;

/**
 * @author chenlin
 */
public interface AddressDao {
    List<Address> queryAddress(int userId);
    void insert(Address address);
    void updateDft(String id);
    void delete(String id);
}
