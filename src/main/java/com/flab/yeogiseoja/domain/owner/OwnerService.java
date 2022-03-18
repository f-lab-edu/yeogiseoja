package com.flab.yeogiseoja.domain.owner;

public interface OwnerService {
    OwnerInfo registerOwner(OwnerCommand.RegisterOwnerRequest request);

    OwnerInfo updateOwner(OwnerCommand.UpdateOwnerRequest request);

    OwnerInfo removeOwner(OwnerCommand.RemoveOwnerRequest request);

    OwnerInfo passwordChangeOwner(OwnerCommand.OwnerPasswordChangeRequest request);
}
