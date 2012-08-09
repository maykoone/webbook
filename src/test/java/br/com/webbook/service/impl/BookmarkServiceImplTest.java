/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Bookmark;
import br.com.webbook.service.BookmarkService;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author maykoone
 */
public class BookmarkServiceImplTest {

    ApplicationContext ctx;
    BookmarkService service;

    public BookmarkServiceImplTest() {
        ctx = new ClassPathXmlApplicationContext(
                "classpath:META-INF/applicationContext.xml");
        service = (BookmarkService) ctx.getBean("bookmarkService");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class BookmarkServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Bookmark bookmark = null;
        Bookmark expResult = null;
        Bookmark result = service.save(bookmark);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class BookmarkServiceImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Bookmark bookmark = null;
        service.remove(bookmark);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class BookmarkServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        Long id = null;
        Bookmark expResult = null;
        Bookmark result = service.findById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class BookmarkServiceImpl.
     */
    @Test
    public void testList() {
        System.out.println("list");
        List expResult = null;
        List result = service.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
