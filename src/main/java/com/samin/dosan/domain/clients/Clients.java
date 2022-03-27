package com.samin.dosan.domain.clients;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Long id;

    @NotBlank
    @Column(length = 50, nullable = false)
    private String clientNm;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String manager;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private ClientType clientType;

    @NotBlank
    @Column(length = 15, nullable = false)
    private String phoneNum;

    @Column(length = 15)
    private String officeNum;

    @Column(length = 15)
    private String faxNum;

    @Column(length = 100, nullable = false)
    private String email;

    @Embedded
    private Address address = new Address();

    @Column(length = 20)
    private String bussinessNum;

    @Column(columnDefinition = "TEXT")
    private String etc;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public static Clients of(Clients saveData) {
        Clients clients = new Clients();
        clients.clientNm = saveData.clientNm;
        clients.manager = saveData.manager;
        clients.clientType = saveData.clientType;
        clients.phoneNum = saveData.phoneNum;
        clients.officeNum = saveData.officeNum;
        clients.faxNum = saveData.faxNum;
        clients.email = saveData.email;
        clients.address = saveData.address;
        clients.bussinessNum = saveData.bussinessNum;
        clients.etc = saveData.etc;

        clients.used = Used.Y;

        return clients;
    }

    @Builder(builderMethodName = "test")
    public Clients(Long id, String clientNm, String manager, ClientType clientType, String phoneNum, String officeNum, String faxNum, String email, Address address, String bussinessNum, String etc, Used used) {
        this.id = id;
        this.clientNm = clientNm;
        this.manager = manager;
        this.clientType = clientType;
        this.phoneNum = phoneNum;
        this.officeNum = officeNum;
        this.faxNum = faxNum;
        this.email = email;
        this.address = address;
        this.bussinessNum = bussinessNum;
        this.etc = etc;
        this.used = used;
    }

    public void update(Clients updateData) {
        this.clientNm = updateData.clientNm;
        this.manager = updateData.manager;
        this.clientType = updateData.clientType;
        this.phoneNum = updateData.phoneNum;
        this.officeNum = updateData.officeNum;
        this.faxNum = updateData.faxNum;
        this.email = updateData.email;
        this.address = updateData.address;
        this.bussinessNum = updateData.bussinessNum;
        this.etc = updateData.etc;
    }

    public void delete() {
        this.used = Used.N;
    }
}
