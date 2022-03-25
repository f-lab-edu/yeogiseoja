package com.flab.yeogiseoja.domain.owner;

public interface OwnerService {
    OwnerInfo.OwnerSummaryInfo registerOwner(OwnerCommand.RegisterOwnerRequest request);

    OwnerInfo.OwnerSummaryInfo updateOwner(OwnerCommand.UpdateOwnerRequest request);

    boolean removeOwner(OwnerCommand.RemoveOwnerRequest request);

    boolean passwordChangeOwner(OwnerCommand.OwnerPasswordChangeRequest request);
}
