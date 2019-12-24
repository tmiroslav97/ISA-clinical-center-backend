package clinic.centersystem.model;

import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = DbTableConstants.PRICELISTITEM)
public class PriceListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.SERVICENAME, nullable = false)
    private String serviceName;

    @Column(name = DbColumnConstants.PRICE, nullable = false)
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PriceList priceList;

    public PriceListItem() {
        // TODO: implement
    }

}