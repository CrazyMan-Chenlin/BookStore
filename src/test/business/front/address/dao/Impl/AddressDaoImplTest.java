package test.business.front.address.dao.Impl; 

import business.front.address.dao.impl.AddressDaoImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* AddressDaoImpl Tester. 
*
*/ 
public class AddressDaoImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: insert(Address address) 
* 
*/ 
@Test
public void testInsert() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryAddress(int userId) 
* 
*/ 
@Test
public void testQueryAddress() throws Exception {
    new AddressDaoImpl().queryAddress(1);
} 

/** 
* 
* Method: delete(String id) 
* 
*/ 
@Test
public void testDelete() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: handle(ResultSet rs) 
* 
*/ 
@Test
public void testHandle() throws Exception { 
//TODO: Test goes here... 
} 


} 
