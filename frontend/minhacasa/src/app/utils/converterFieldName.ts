class ConverterFieldName {
     static fields: string[] = [
          'immobileTitle', 'address', 'state', 'city', 'neighborhood', 'price', 
          'category', 'sellerType', 'description', 'garage', 'numberOfRooms', 
          'numberOfBathrooms', 'propertyCondition', 'numberOfBedrooms', 'iptuValue', 
          'hasSuite', 'propertyAge', 'propertyType', 'totalArea', 'hasGarden'
     ];

     static fieldMap: { [key: string]: string } = {
          'immobileTitle': 'titulo do imóvel',
          'address': 'endereço',
          'state': 'estado',
          'city': 'cidade',
          'neighborhood': 'bairro',
          'price': 'preço',
          'category': 'o que você quer fazer',
          'sellerType': 'que tipo de vendedor você é',
          'description': 'descrição',
          'garage': 'seu imóvel possui garagem',
          'numberOfRooms': 'quantidade de cômodos',
          'numberOfBathrooms': 'quantidade de banheiros',
          'propertyCondition': 'integridade do imóvel',
          'numberOfBedrooms': 'quantidade de quartos do imóvel',
          'iptuValue': 'valor do IPTU',
          'hasSuite': 'seu imóvel possui suíte',
          'propertyAge': 'qual a idade do seu imóvel',
          'propertyType': 'tipo do imóvel',
          'totalArea': 'qual a área total',
          'hasGarden': 'possui jardim'
     }

     static veirify(fieldName: string): string {
          if (this.fields.includes(fieldName)) {
               return this.converter(fieldName);
          }

          return 'ocorreu um erro';
     }

     static converter(field: string): string {
          if (field in this.fieldMap) {
               return this.fieldMap[field];
          }

          return 'ocorreu um erro';
     }
}