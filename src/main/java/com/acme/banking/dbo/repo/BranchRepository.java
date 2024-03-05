package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Branch;

import java.util.Optional;

public interface BranchRepository {

    Optional<Branch> getBranchById(int id);
}
