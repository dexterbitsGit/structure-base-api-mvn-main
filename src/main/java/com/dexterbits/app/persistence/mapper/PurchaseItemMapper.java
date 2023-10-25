package com.dexterbits.app.persistence.mapper;

import com.dexterbits.app.domain.PurchaseItem;
import com.dexterbits.app.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

  @Mappings({
      @Mapping(source = "id.idProducto", target = "productId"),
      @Mapping(source = "cantidad", target = "quantity"),
      @Mapping(source = "estado", target = "active")
  })
  PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);

  @InheritInverseConfiguration
  @Mappings({
      @Mapping(target = "compra", ignore = true),
      @Mapping(target = "producto", ignore = true),
      @Mapping(target = "id.idCompra", ignore = true)
  })
  ComprasProducto toComprasProducto(PurchaseItem purchaseItem);

}
