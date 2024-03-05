package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.repo.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class ReportingTest {

    BranchRepository branchRepo;

    @BeforeEach
    public void setUp() {
        branchRepo = mock(BranchRepository.class);
    }

    @Test
    public void shouldReturnEmptyReportWhenBranchDontHaveAccunts(){
        Reporting sut = new Reporting(branchRepo);
        int branchId = 1;
        Branch dummyBranch = new Branch(new ArrayList<>());

        when(branchRepo.getBranchById(branchId)).thenReturn(Optional.of(dummyBranch));

        assertEquals("", sut.getReport(branchId));
    }

    @Test
    public void shouldThrowExceptionThenNoBranchFinded() {
        var branchId = 1;
        when(branchRepo.getBranchById(branchId)).thenReturn(Optional.empty());
        Reporting sut = new Reporting(branchRepo);

        assertThrows(IllegalStateException.class, () -> sut.getReport(branchId));
    }

    @Test
    public void shouldReturnInfoAboutEmptyChildrenThenBranchDontHaveChildren() {

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
