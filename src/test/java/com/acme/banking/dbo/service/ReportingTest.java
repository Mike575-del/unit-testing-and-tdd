package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ReportingTest {

    @Test
    public void shouldReturnEmptyReportWhenBranchIsEmpty(){
        Reporting sut = new Reporting();
        Branch dummyBranch = new Branch(new ArrayList<>());
        assertEquals("", sut.getReport(dummyBranch));
    }







    /*
    "# BRANCH 1 (10000.0)" +
            "  ## (1) Z K" +
            "       ### acc 0: 100." +
            "  # BRANCH 1-1 (20000.0)" +
            "    ## (2) Vas Pupking" +
            "       ### acc 1: 100.0" +
            "       ### acc 2: 200.0"
*/
}
