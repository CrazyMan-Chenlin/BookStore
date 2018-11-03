package business.front.address.dao;

import entity.Address;

import java.util.List;

public interface AddressDao {
    List<Address> queryAddress(int userId);
}
