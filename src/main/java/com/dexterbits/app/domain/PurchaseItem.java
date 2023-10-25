package com.dexterbits.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {

  private int productId;

  private int quantity;

  private double total;

  private boolean active;

}
