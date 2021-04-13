package Enum;

public enum Directive {
    N_CONNECT_req(new byte[]{0,0,0,0,1,0,1,1}),
    N_DATA_req(new byte[]{0}),
    N_DISCONNECT_req(new byte[]{0,0,0,1,0,0,1,1});

    byte[] directive;

    private Directive(byte[] table) {
        this.directive = table;
    }
  }