package br.com.fiap.inventorycontroluser.controllers.jsons.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenResponseJson {

  private UserResponseJson user;
  private String token;
  private String type;
}
