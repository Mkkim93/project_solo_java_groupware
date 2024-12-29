package com.group.domain.mail.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MailRecvStoreId implements Serializable {

    private Integer empId;
    private Integer mailboxId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailRecvStoreId that = (MailRecvStoreId) o;
        return Objects.equals(empId, that.empId) && Objects.equals(mailboxId, that.mailboxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, mailboxId);
    }
}
