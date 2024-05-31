CREATE TABLE IF NOT EXISTS "Cliente" (
  "idCliente" INTEGER PRIMARY KEY AUTOINCREMENT,
  "cpf" TEXT UNIQUE,
  "nome" TEXT NOT NULL,
  "email" TEXT,
  "telefone" TEXT NOT NULL,
  "dataNascimento" TEXT NOT NULL,
  "idEndereco" INTEGER,
  FOREIGN KEY ("idEndereco") REFERENCES "Endereco" ("IdEndereco")
);

CREATE TABLE IF NOT EXISTS "Endereco" (
  "IdEndereco" INTEGER PRIMARY KEY AUTOINCREMENT,
  "cep" TEXT NOT NULL,
  "logradouro" TEXT NOT NULL,
  "numero" TEXT NOT NULL,
  "complemento" TEXT,
  "bairro" TEXT NOT NULL,
  "cidade" TEXT NOT NULL,
  "uf" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Pagamento" (
  "idPagamento" INTEGER PRIMARY KEY AUTOINCREMENT,
  "idCliente" INTEGER,
  "idPasseio" INTEGER,
  "situacao" TEXT NOT NULL,
  FOREIGN KEY ("idCliente") REFERENCES "Cliente" ("idCliente"),
  FOREIGN KEY ("idPasseio") REFERENCES "Passeio" ("idPasseio")
);

CREATE TABLE IF NOT EXISTS "Passeio" (
  "idPasseio" INTEGER PRIMARY KEY AUTOINCREMENT,
  "idDestino" INTEGER,
  "idOnibus" INTEGER,
  "data" TEXT NOT NULL,
  "hora" TEXT NOT NULL,
  "preco" REAL NOT NULL,
  FOREIGN KEY ("idDestino") REFERENCES "Destino" ("idDestino"),
  FOREIGN KEY ("idOnibus") REFERENCES "Onibus" ("idOnibus")
);

CREATE TABLE IF NOT EXISTS "Destino" (
  "idDestino" INTEGER PRIMARY KEY AUTOINCREMENT,
  "nome" TEXT UNIQUE,
  "idEndereco" INTEGER,
  FOREIGN KEY ("idEndereco") REFERENCES "Endereco" ("IdEndereco")
);

CREATE TABLE IF NOT EXISTS "Onibus" (
  "idOnibus" INTEGER PRIMARY KEY AUTOINCREMENT,
  "placa" TEXT UNIQUE,
  "modelo" TEXT NOT NULL,
  "empresa" TEXT NOT NULL,
  "tipo" TEXT NOT NULL
);