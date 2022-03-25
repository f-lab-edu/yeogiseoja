package com.flab.yeogiseoja.application.owner;

import com.flab.yeogiseoja.domain.owner.OwnerCommand;
import com.flab.yeogiseoja.domain.owner.OwnerInfo;
import com.flab.yeogiseoja.domain.owner.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerFacade {
    private final OwnerService ownerService;

    public OwnerInfo.OwnerSummaryInfo registerOwner(OwnerCommand.RegisterOwnerRequest ownerCommand) {
        var owner = ownerService.registerOwner(ownerCommand);
        return owner;
    }
}
