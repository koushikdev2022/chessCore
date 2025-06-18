// package com.iksen.chessCore.mapper.plan;

// import com.iksen.chessCore.dto.plan.PlanDTO;
// import com.iksen.chessCore.dto.plan.PlanAccessDTO;
// import com.iksen.chessCore.dto.plan.PlanAccessMapDTO;
// import com.iksen.chessCore.dto.plan.PlanPriceDTO;
// import com.iksen.chessCore.model.Plan;
// import com.iksen.chessCore.model.PlanAccess;
// import com.iksen.chessCore.model.PlanAccessMap;
// import com.iksen.chessCore.model.PlanPrice;

// import java.util.List;
// import java.util.stream.Collectors;

// public class PlanMapper {

//     public static PlanDTO toDTO(Plan plan) {
//         if (plan == null) {
//             return null;
//         }
//         return new PlanDTO(
//                 plan.getId(),
//                 plan.getPlanName(),
//                 plan.getPlanShortName(),
//                 plan.getPlanDescription(),
//                 plan.getStatus(),
//                 plan.getCreatedAt(),
//                 plan.getUpdatedAt(),
//                 plan.getAvatar(),
//                 plan.getPrices() != null ? plan.getPrices().stream().map(PlanMapper::toPriceDTO).toList() : null,
//                 plan.getAccessMappings() != null ? plan.getAccessMappings().stream().map(PlanMapper::toAccessDTO).toList() : null
//         );
//     }

//     public static PlanPriceDTO toPriceDTO(PlanPrice price) {
//         if (price == null) {
//             return null;
//         }
//         return new PlanPriceDTO(
//                 price.getId(),
//                 price.getCurrency(),
//                 price.getPrice(),
//                 price.getCountryId(),
//                 price.getStatus(),
//                 price.getCreatedAt(),
//                 price.getUpdatedAt()
//         );
//     }

//    public static PlanAccessDTO toAccessDTO(PlanAccessMap accessMap) {
//         if (accessMap == null || accessMap.getAccess() == null) {
//             return null;
//         }
        
//         // Access the related PlanAccess through the mapping
//         PlanAccess access = accessMap.getAccess();
        
//         return new PlanAccessDTO(
//             access.getId(),
//             access.getAccessName(),
//             access.getPlanDescription(),
//             access.getStatus(),
//             access.getCreatedAt(),
//             access.getUpdatedAt()
//         );
//     }

//     public static List<PlanDTO> toDTOList(List<Plan> plans) {
//         return plans.stream()
//                 .map(PlanMapper::toDTO)
//                 .collect(Collectors.toList());
//     }
//     public static PlanAccessMapDTO toAccessMapDTO(PlanAccessMap accessMap) {
//             if (accessMap == null || accessMap.getAccess() == null) {
//                 return null;
//             }

//             PlanAccess access = accessMap.getAccess();

//             return new PlanAccessMapDTO(
//                 accessMap.getId(),
                
//                 accessMap.getDesc(),
//                 accessMap.getStatus(),
//                 accessMap.getCreatedAt(),
//                 accessMap.getUpdatedAt(),
//                 new PlanAccessDTO(
//                     access.getId(),
//                     access.getAccessName(),
//                     access.getPlanDescription(),
//                     access.getStatus(),
//                     access.getCreatedAt(),
//                     access.getUpdatedAt()
//                 )
//             );
//     }
// }
package com.iksen.chessCore.mapper.plan;

import com.iksen.chessCore.dto.plan.PlanDTO;
import com.iksen.chessCore.dto.plan.PlanAccessDTO;
import com.iksen.chessCore.dto.plan.PlanAccessMapDTO;
import com.iksen.chessCore.dto.plan.PlanPriceDTO;
import com.iksen.chessCore.model.Plan;
import com.iksen.chessCore.model.PlanAccess;
import com.iksen.chessCore.model.PlanAccessMap;
import com.iksen.chessCore.model.PlanPrice;

import java.util.List;
import java.util.stream.Collectors;

public class PlanMapper {

    public static PlanDTO toDTO(Plan plan) {
        if (plan == null) {
            return null;
        }
        return new PlanDTO(
                plan.getId(),
                plan.getPlanName(),
                plan.getPlanShortName(),
                plan.getPlanDescription(),
                plan.getStatus(),
                plan.getCreatedAt(),
                plan.getUpdatedAt(),
                plan.getAvatar(),
                plan.getPrices() != null ? plan.getPrices().stream().map(PlanMapper::toPriceDTO).toList() : null,
                plan.getAccessMappings() != null ? plan.getAccessMappings().stream().map(PlanMapper::toAccessMapDTO).toList() : null
        );
    }

    public static PlanPriceDTO toPriceDTO(PlanPrice price) {
        if (price == null) {
            return null;
        }
        return new PlanPriceDTO(
                price.getId(),
                price.getCurrency(),
                price.getPrice(),
                price.getCountryId(),
                price.getStatus(),
                price.getCreatedAt(),
                price.getUpdatedAt()
        );
    }
    public static PlanAccessDTO toAccessDTO(PlanAccessMap accessMap) {
        if (accessMap == null || accessMap.getAccess() == null) {
            return null;
        }

        PlanAccess access = accessMap.getAccess();

        return new PlanAccessDTO(
                access.getId(),
                access.getAccessName(),
                access.getPlanDescription(),
                access.getStatus(),
                access.getCreatedAt(),
                access.getUpdatedAt()
        );
    }
    public static PlanAccessMapDTO toAccessMapDTO(PlanAccessMap accessMap) {
        if (accessMap == null || accessMap.getAccess() == null) {
            return null;
        }

        PlanAccess access = accessMap.getAccess();

        return new PlanAccessMapDTO(
                accessMap.getId(),
                accessMap.getDesc(),               
                accessMap.getStatus(),
                accessMap.getCreatedAt(),
                accessMap.getUpdatedAt(),
                new PlanAccessDTO(
                        access.getId(),
                        access.getAccessName(),
                        access.getPlanDescription(),
                        access.getStatus(),
                        access.getCreatedAt(),
                        access.getUpdatedAt()
                )
        );
    }

    public static List<PlanDTO> toDTOList(List<Plan> plans) {
        return plans.stream()
                .map(PlanMapper::toDTO)
                .collect(Collectors.toList());
    }
}
