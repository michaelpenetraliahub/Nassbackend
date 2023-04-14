package com.jtb.taxpayerws.entity.audit;

import com.jtb.taxpayerws.service.impl.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditAwareimpl implements AuditorAware<Long> {
    @Override
    public Optional <Long> getCurrentAuditor() {
        try {
            UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return Optional.of( principal.getId());
        } catch (RuntimeException ex) {
//        If value is 0: Then the user is a default user
//        When there is no logged-in user, an NPE is thrown because principal == null
            return Optional.of(0L);
        }
    }
}
