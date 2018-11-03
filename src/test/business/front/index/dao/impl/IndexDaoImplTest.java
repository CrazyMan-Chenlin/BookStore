package test.business.front.index.dao.impl; 

import business.front.index.dao.impl.IndexDaoImpl;
import entity.Books;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.awt.print.Book;

/** 
* IndexDaoImpl Tester. 
* 
*
*/
public class IndexDaoImplTest { 

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
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryBooks(int TypeId) 
* 
*/ 
@Test
public void testQueryBooks() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryBook(int id) 
* 
*/ 
@Test
public void testQueryBook() throws Exception {
    Books book = new IndexDaoImpl().queryBook("1");
    System.out.println(book.toString());
}
} 
