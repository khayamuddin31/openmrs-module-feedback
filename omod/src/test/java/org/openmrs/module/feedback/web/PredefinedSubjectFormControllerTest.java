/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */



package org.openmrs.module.feedback.web;

//~--- non-JDK imports --------------------------------------------------------

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openmrs.api.context.Context;
import org.openmrs.module.feedback.FeedbackService;
import org.openmrs.module.feedback.PredefinedSubject;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

//~--- JDK imports ------------------------------------------------------------

import java.lang.String;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PredefinedSubjectFormControllerTest extends BaseModuleWebContextSensitiveTest {
    private Boolean                         expResult = true;
    private Boolean                         result    = false;
    private PredefinedSubjectFormController controller;
    private MockHttpServletRequest          request;
    private HttpServletResponse             response;
    private FeedbackService                 service;

    public PredefinedSubjectFormControllerTest() {}

    @BeforeClass
    public static void setUpClass() throws Exception {}

    @AfterClass
    public static void tearDownClass() throws Exception {}

    @Before
    public void setUp() throws Exception {

        /* executed before the test is run */
        this.service    = Context.getService(FeedbackService.class);
        this.controller = new PredefinedSubjectFormController();
        this.request    = new MockHttpServletRequest();
        this.response   = new MockHttpServletResponse();

        /* this file is in the same folder of test resources where the hibernate mapping file is located */
        initializeInMemoryDatabase();
        executeDataSet("PredefinedSubjectDataset.xml");

        /* Sample data is loaded into the system */
        authenticate();
    }

    @After
    public void tearDown() {}

    /**
     * Test of formBackingObject method, of class PredefinedSubjectFormController.
     */
    @Test
    public void testFormBackingObject() throws Exception {
        HttpServletRequest req = null;

        authenticate();

        PredefinedSubjectFormController instance = new PredefinedSubjectFormController();

        System.out.println("formBackingObject");

        /* To check wheather the delete works or not */
        this.request = new MockHttpServletRequest();

        ModelAndView mv = new ModelAndView();

        instance.setSuccessView("someValue");
        request.setSession(new MockHttpSession(null));
        request.setMethod("POST");
        request.setParameter("predefinedsubjectid", "1");
        request.setParameter("delete", "1");
        mv = instance.handleRequest(request, response);

        if (service.getPredefinedSubject(1) == null) {
            result = true;
            Assert.assertEquals(expResult, result);
        } else {
            result = false;
            Assert.assertEquals(expResult, result);
        }

        /* To check that anything except 1 in delete should not delete predefined subject */
        this.request = new MockHttpServletRequest();
        mv           = new ModelAndView();
        instance.setSuccessView("someValue");
        request.setSession(new MockHttpSession(null));
        request.setMethod("POST");
        request.setParameter("predefinedsubjectid", "2");
        request.setParameter("delete", "0");
        mv = instance.handleRequest(request, response);

        if (service.getPredefinedSubject(2) != null) {
            result = true;
            Assert.assertEquals(expResult, result);
        } else {
            result = false;
            Assert.assertEquals(expResult, result);
        }

        /* To check wheather the save works or not */
        this.request = new MockHttpServletRequest();
        mv           = new ModelAndView();
        instance.setSuccessView("someValue");
        request.setSession(new MockHttpSession(null));
        request.setMethod("POST");
        request.setParameter("predefinedsubjectid", "3");
        request.setParameter("predefinedsubject", "Testsave");
        request.setParameter("save", "1");
        mv = instance.handleRequest(request, response);

        if ("Testsave".equals(service.getPredefinedSubject(3).getSubject())) {
            result = true;
            Assert.assertEquals(expResult, result);
        } else {
            result = false;
            Assert.assertEquals(expResult, result);
        }

        /* To check wheather the save works or not with wrong value of save i.e. save != 1 */
        this.request = new MockHttpServletRequest();
        mv           = new ModelAndView();
        instance.setSuccessView("someValue");
        request.setSession(new MockHttpSession(null));
        request.setMethod("POST");
        request.setParameter("predefinedsubjectid", "4");
        request.setParameter("predefinedsubject", "Testsave");
        request.setParameter("save", "0");
        mv = instance.handleRequest(request, response);

        if ("Test".equals(service.getPredefinedSubject(4).getSubject())) {
            result = true;
            Assert.assertEquals(expResult, result);
        } else {
            result = false;
            Assert.assertEquals(expResult, result);
        }
    }

    /**
     * No test required as this is just doing the message showing part and no editing/adding in database just getting the feedback predefined subject incase the predefinedsubjectid exists.
     * Test of referenceData method, of class PredefinedSubjectFormController.
     *
     * @Test
     * public void testReferenceData() throws Exception {
     *       System.out.println("referenceData");
     *       HttpServletRequest req = null;
     *       PredefinedSubjectFormController instance = new PredefinedSubjectFormController();
     *       Map expResult = null;
     *       Map result = instance.referenceData(req);
     *       assertEquals(expResult, result);
     *       // TODO review the generated test code and remove the default call to fail.
     *       fail("The test case is a prototype.");
     * }
     */
}


//~ Formatted by Jindent --- http://www.jindent.com
