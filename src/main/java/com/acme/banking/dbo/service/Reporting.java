package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Branch;
import com.acme.banking.dbo.repo.AccountRepository;
import com.acme.banking.dbo.repo.BranchRepository;
import com.acme.banking.dbo.repo.ClientRepository;

import java.util.Optional;

public class Reporting {
    /**
     * @return Markdown report for all branches, clients, accounts
     */

    BranchRepository branchRepo;

    public Reporting (BranchRepository branchRepository){
        this.branchRepo = branchRepository;

    }
    public String getReport(int branchId) {

        Optional<Branch> optionalRootBranch = branchRepo.getBranchById(branchId);

        if(optionalRootBranch.isEmpty()) {
            throw new IllegalStateException("Cannot find branch with " + branchId +" in repository");
        }

        Branch rootBranch = optionalRootBranch.get();

        if(rootBranch.getAccounts().isEmpty()) {
            return "";
        }

        if(rootBranch.getChildren().isEmpty()){
            return "" + "Branch " + branchId + "doesn't have children";
        }

        return "";
    }
}
