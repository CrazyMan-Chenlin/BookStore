package test.business.front.index.dao.impl; 

import business.front.index.dao.impl.IndexDaoImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* BookTypesDaoImpl Tester. 

*/ 
public class BookTypesDaoImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: queryAll() 
* 
*/ 
@Test
public void testQueryAll() throws Exception {
    IndexDaoImpl bookTypesDao =new IndexDaoImpl();
    bookTypesDao.queryAll();
} 


} 
