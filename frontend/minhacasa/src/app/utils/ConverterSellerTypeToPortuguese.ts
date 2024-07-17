export class ConverterSellerTypeToPortuguese {
     static converter(sellerType: string): string {
          switch (sellerType) {
               case 'OWNER':
                    return 'proprietário';
               case 'BROKER':
                    return 'corretora';
               case 'REAL_ESTATE':
                    return 'imobiliária';
               default:
                    return 'vendedor não definido';
          }
     }
}