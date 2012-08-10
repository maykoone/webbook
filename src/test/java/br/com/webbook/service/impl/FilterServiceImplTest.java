/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Filter;
import br.com.webbook.service.FilterService;
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
public class FilterServiceImplTest {

    ApplicationContext ctx;
    FilterService service;

    public FilterServiceImplTest() {
        ctx = new ClassPathXmlApplicationContext(
                "classpath:META-INF/applicationContext.xml");
        service = (FilterService) ctx.getBean("filterService");
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
     * Test of save method, of class FilterServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("Test of save method, of class FilterServiceImpl");
        Filter filter = new Filter();
        filter.setTitle("first filter");

        Filter result = service.save(filter);

        assertNotNull(result);
    }

    /**
     * Test of remove method, of class FilterServiceImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Test of remove method, of class FilterServiceImpl");
        Filter filter = new Filter();
        filter.setTitle("first filter");

        Filter result = service.save(filter);

        service.remove(result);
    }

    /**
     * Test of findById method, of class FilterServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Test of findById method, of class FilterServiceImpl");
        Filter filter = new Filter();
        filter.setTitle("first filter");

        filter = service.save(filter);

        Filter resultFind = service.findById(filter.getId());

        assertNotNull(resultFind);

        try {
            assertEquals(filter, resultFind);
        } catch (Exception e) {
            fail("resultado não é igual ao esperado");
        }

    }

    /**
     * Test of list method, of class FilterServiceImpl.
     */
    @Test
    public void testList() {
        System.out.println("Test of list method, of class FilterServiceImpl");
        List<Filter> resultList = service.list();

        assertNotNull(resultList);
    }
}
