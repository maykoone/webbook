/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.User;
import br.com.webbook.service.UserService;
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
public class UserServiceImplTest {

    ApplicationContext ctx;
    UserService service;

    public UserServiceImplTest() {
        ctx = new ClassPathXmlApplicationContext(
                "classpath:META-INF/beans_test.xml");
        service = (UserService) ctx.getBean("userService");
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
     * Test of save method, of class UserServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("Test of save method, of class UserServiceImpl");
        User user = new User("1234", "maykoone@gmail.com", "maykooneSave");
        User result = service.save(user);

        assertNotNull(result);
    }

    /**
     * Test of remove method, of class UserServiceImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Test of remove method, of class UserServiceImpl");
        User user = new User("1234", "maykoone@gmail.com", "maykooneRemove");
        User result = service.save(user);

        service.remove(user);
    }

    /**
     * Test of findById method, of class UserServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Test of findById method, of class UserServiceImpl");
        User user = new User("1234", "maykoone@gmail.com", "maykooneFind");
        user = service.save(user);

        User resultFind = service.findById(user.getId());

        assertNotNull(resultFind);
        try {
            assertEquals(user, resultFind);
        } catch (Exception e) {
            fail("resultado não é ao esperado");
        }

    }

    /**
     * Test of list method, of class UserServiceImpl.
     */
    @Test
    public void testList() {
        System.out.println("Test of list method, of class UserServiceImpl");
        List<User> resultList = service.list();
        assertNotNull(resultList);
    }
}
