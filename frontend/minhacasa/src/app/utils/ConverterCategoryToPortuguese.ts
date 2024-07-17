export class ConverterCategoryToPortuguese {
     static converter(category: string): string {
          switch (category) {
               case 'SELL':
                    return 'vender';
               case "RENT":
                    return 'alugar';
               case "TEMPORARY_RENTAL":
                    return "alugar por temporada";
               case "FINANCING":
                    return "financiamento";
               default:
                    return "cagoria não especificada!";
          } 
     }
}