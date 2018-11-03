package business.front.address.service.Impl;
import business.front.address.dao.Impl.AddressDaoImpl;
import business.front.address.service.AddressService;
import entity.Address;
import java.util.List;

public class AddressServiceImpl implements AddressService {
     private static AddressDaoImpl addressDao = new AddressDaoImpl();
    @Override
    public void insert(Address address)  {
            addressDao.insert(address);

    }
    @Override
    public List<Address> queryAddress(int userId) {
        List<Address> addresses = addressDao.queryAddress(userId);
        return addresses;
    }

    @Override
    public void delete(String id) {
        addressDao.delete(id);
    }

    @Override
    public void updateDft(String id) {
        addressDao.updateDft(id);
    }
}
