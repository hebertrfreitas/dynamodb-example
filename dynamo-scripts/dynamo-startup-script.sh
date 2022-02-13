echo "dynamodb host="$DYNAMODB_HOST
echo "dynamodb port="$DYNAMODB_PORT

while ! nc -z $DYNAMODB_HOST $DYNAMODB_PORT; do 
  echo "esperando dynamodb local ficar disponível na porta 8000"; 
  sleep 5;
done

echo "dynamodb disponível"

aws --endpoint-url http://$DYNAMODB_HOST:$DYNAMODB_PORT dynamodb scan --table-name $DYNAMODB_TABLE > /dev/null

if [ $? -eq 0 ] 
then 
  echo "Tabela já existe, dando skip no script de inicialização" 
else 
  echo "Tabela ainda não existe, executando script de inicialização ..."

  echo "Criando tabela"
  aws --endpoint-url http://$DYNAMODB_HOST:$DYNAMODB_PORT dynamodb create-table \
  --table-name $DYNAMODB_TABLE \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5
  echo "Tabela criada com sucesso"
fi


tail /dev/stdout