package com.revature;

import org.junit.*;

public class AnnotationTest {
	
	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("called " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@AfterClass
	public static void runAfterClass() {
		System.out.println("called " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Before
	public void runBefore() {
		System.out.println("called " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@After
	public void runAfter() {
		System.out.println("called " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	
	
	
	@Test
	public void runTest1() {
		System.out.println("called " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	public void runTest2() {
		System.out.println("called " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	
}




