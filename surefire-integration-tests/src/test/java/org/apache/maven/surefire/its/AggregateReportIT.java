package org.apache.maven.surefire.its;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import org.apache.maven.surefire.its.misc.HelperAssertions;

/**
 * Test report aggregation
 *
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 */
public class AggregateReportIT
    extends SurefireVerifierTestClass
{

    public AggregateReportIT()
    {
        super( "/aggregate-report" );
    }

    public void testAggregateReport()
        throws Exception
    {
        execute( "org.apache.maven.plugins:maven-surefire-report-plugin:" + getSurefireVersion() + ":report" );

        File surefireReportHtml = getSiteFile( "surefire-report.html" );
        assertTrue( "surefire report missing: " + surefireReportHtml.getAbsolutePath(), surefireReportHtml.exists() );

        // TODO HtmlUnit tests on the surefire report

        File[] testDirs = new File[2];
        testDirs[0] = new File( getTestDir(), "child1" );
        testDirs[1] = new File( getTestDir(), "child2" );
        IntegrationTestSuiteResults suite = HelperAssertions.parseTestResults( testDirs );
        HelperAssertions.assertTestSuiteResults( 2, 0, 1, 0, suite );
    }
}
