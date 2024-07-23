export class ConverterTypeForPortuguese {
     static converter(type: string): string {
          switch (type) {
               case "HOUSE":
                    return "Casa";
               case "ROOM":
                    return "Quarto";
               case "ROOF":
                    return "Cobertura";
               case "FLAT":
                    return "Apartamento";
               case "KITNET":
                    return "Kitnet";
               case "LOFT":
                    return "Loft";
               case "STUDIO":
                    return "Estúdio";
               case "DUPLEX":
                    return "Duplex";
               case "TRIPLEX":
                    return "Triplex";
               case "CONDOMINIUM":
                    return "Condomínio";
               case "BUILDING":
                    return "Prédio";
               case "SHEDS":
                    return "Galpões";
               case "DEPOSITS":
                    return "Depósito";
               case "OFFICES":
                    return "Escritório";
               case "PARKING":
                    return "Estacionamento";
               case "STORE":
                    return "Loja";
               case "SUBDIVISION":
                    return "Loteamento";
               case "GATED_COMMUNITY":
                    return "Condomínio Fechado";
               case "FARM":
                    return "Fazenda";
               default:
                    return "Tipo não especificado";
          }
     }
}