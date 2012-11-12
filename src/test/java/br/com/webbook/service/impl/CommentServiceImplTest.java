/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.webbook.service.impl;

import br.com.webbook.domain.Comment;
import br.com.webbook.service.CommentService;
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
public class CommentServiceImplTest {

    ApplicationContext ctx;
    CommentService service;

    public CommentServiceImplTest() {
        ctx = new ClassPathXmlApplicationContext(
                "classpath:META-INF/beans_test.xml");
        service = (CommentService) ctx.getBean("commentService");
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
     * Test of save method, of class CommentServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("Test of save method, of class CommentServiceImpl");
        Comment comment = new Comment();
        comment.setText("first comment");

        Comment result = service.save(comment);
        assertNotNull(result);
    }

    /**
     * Test of remove method, of class CommentServiceImpl.
     */
    @Test
    public void testRemove() {
        System.out.println("Test of remove method, of class CommentServiceImpl");
        Comment comment = new Comment();
        comment.setText("first comment");

        Comment result = service.save(comment);

        service.remove(comment);
    }

    /**
     * Test of findById method, of class CommentServiceImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("Test of findById method, of class CommentServiceImpl");
        Comment comment = new Comment();
        comment.setText("first comment");

        comment = service.save(comment);
        Comment resultFind = service.findById(comment.getId());

        assertNotNull(resultFind);
        try {
            assertEquals(comment, resultFind);
        } catch (Exception e) {
            fail("resultado não é igual ao esperado");
        }
    }

    /**
     * Test of list method, of class CommentServiceImpl.
     */
    @Test
    public void testList() {
        System.out.println("Test of list method, of class CommentServiceImpl");
        List<Comment> resultList = service.list();
        assertNotNull(resultList);
    }
}
