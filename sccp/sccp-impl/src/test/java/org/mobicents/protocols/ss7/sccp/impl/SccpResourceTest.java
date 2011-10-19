/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.sccp.impl;

import org.testng.annotations.*;

import static org.testng.Assert.*;

/**
 * @author amit bhayani
 * 
 */
public class SccpResourceTest {

	public SccpResourceTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@BeforeMethod
	public void setUp() {
		SccpResource resource = new SccpResource();
		resource.start();
		resource.getRemoteSpcs().clear();
		resource.getRemoteSsns().clear();
		resource.stop();
	}

	@AfterMethod
	public void tearDown() {
	}
	
	@Test(groups = { "sccpresource","functional.encode"})
	public void testSerialization() throws Exception {
		SccpResource resource = new SccpResource();
		resource.start();

		RemoteSignalingPointCode rsp1 = new RemoteSignalingPointCode(6034, 0, 0);
		RemoteSignalingPointCode rsp2 = new RemoteSignalingPointCode(6045, 0, 0);

		RemoteSubSystem rss1 = new RemoteSubSystem(6034, 8, 0);
		RemoteSubSystem rss2 = new RemoteSubSystem(6045, 8, 0);

		resource.addRemoteSpc(1, rsp1);
		resource.addRemoteSpc(2, rsp2);

		resource.addRemoteSsn(1, rss1);
		resource.addRemoteSsn(2, rss2);

		resource.stop();

		resource = null;// just

		SccpResource resource1 = new SccpResource();
		resource1.start();

		assertEquals( resource1.getRemoteSpcs().size(),2);
		RemoteSignalingPointCode rsp1Temp = resource1.getRemoteSpc(1);
		assertNotNull(rsp1Temp);
		assertEquals( rsp1Temp.getRemoteSpc(),6034);
		
		assertEquals( resource1.getRemoteSsns().size(),2);
		RemoteSubSystem rss1Temp = resource1.getRemoteSsn(1);
		assertEquals( rss1Temp.getRemoteSsn(),8);
	}

}