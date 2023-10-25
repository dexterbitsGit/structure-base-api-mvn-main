package com.dexterbits.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

  private int purchaseId;

  private String clientId;

  private LocalDateTime date;

  private String paymentMethod;

  private String comment;

  private String state;

  private List<PurchaseItem> items;

}
