package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.domain.SavingAccount;
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
        int testBranchId = 1;
        String expected = "Branch " + testBranchId + "doesn't have children";

        Branch stubBranch = mock(Branch.class);
        SavingAccount accountDummy = mock(SavingAccount.class);

        ArrayList<Account> accountsDummy = new ArrayList<>();
        accountsDummy.add(accountDummy);

        when(stubBranch.getAccounts()).thenReturn(accountsDummy);
        when(branchRepo.getBranchById(testBranchId)).thenReturn(Optional.of(stubBranch));

        Reporting sut = new Reporting(branchRepo);
        assertTrue(sut.getReport(testBranchId).contains(expected));
        //Branch stubBranch = mock(Branch.class);
    }

    @Test
    public void shouldReturnAccountsBalancesForAllBranchAccounts(){
        int branchId = 1;
        String expectedReport = "### acc 1: 100.0";
        Reporting sut = new Reporting(branchRepo);

        Branch stubBranch = mock(Branch.class);

        SavingAccount accountMock = mock(SavingAccount.class); //Нужно мокать или можно подставить реальный аккаунт коли на него тесты уже есть?
        ArrayList<Account> accountsDummy = new ArrayList<>();
        accountsDummy.add(accountMock);

        when(accountMock.getId()).thenReturn(1);
        when(accountMock.getAmount()).thenReturn(100d);
        when(stubBranch.getAccounts()).thenReturn(accountsDummy);
        when(branchRepo.getBranchById(branchId)).thenReturn(Optional.of(stubBranch));

        assertTrue(sut.getReport(branchId).contains(expectedReport));
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
