CREATE TABLE IF NOT EXISTS "Cliente" (
  "idCliente" INTEGER PRIMARY KEY AUTOINCREMENT,
  "cpf" TEXT,
  "nome" TEXT NOT NULL,
  "email" TEXT,
  "telefone" TEXT NOT NULL,
  "dataNascimento" TEXT NOT NULL,
  "idPagamento" INTEGER,
  "idEndereco" INTEGER,
  "idPasseio" INTEGER,
  FOREIGN KEY ("idPagamento") REFERENCES "Pagamento" ("idPagamento"),
  FOREIGN KEY ("idEndereco") REFERENCES "Endereco" ("IdEndereco"),
  FOREIGN KEY ("idPasseio") REFERENCES "Pagamento" ("idPasseio")
);

CREATE TABLE IF NOT EXISTS "Endereco" (
  "IdEndereco" INTEGER PRIMARY KEY AUTOINCREMENT,
  "logradouro" TEXT NOT NULL,
  "numero" TEXT NOT NULL,
  "complemento" TEXT,
  "cep" TEXT NOT NULL,
  "cidade" TEXT NOT NULL,
  "uf" TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Pagamento" (
  "idPagamento" INTEGER PRIMARY KEY AUTOINCREMENT,
  "idCliente" INTEGER,
  "idPasseio" INTEGER,
  "pagou" TEXT NOT NULL,
  FOREIGN KEY ("idCliente") REFERENCES "Cliente" ("idCliente"),
  FOREIGN KEY ("idPasseio") REFERENCES "Passeio" ("idPasseio")
);

CREATE TABLE IF NOT EXISTS "Passeio" (
  "idPasseio" INTEGER PRIMARY KEY AUTOINCREMENT,
  "idCliente" INTEGER,
  "idDestino" INTEGER,
  "idOnibus" INTEGER,
  "idPagamento" INTEGER,
  "data" TEXT NOT NULL,
  "hora" TEXT NOT NULL,
  "preco" REAL NOT NULL,
  FOREIGN KEY ("idCliente") REFERENCES "Pagamento" ("idCliente"),
  FOREIGN KEY ("idDestino") REFERENCES "Destino" ("idDestino"),
  FOREIGN KEY ("idOnibus") REFERENCES "Onibus" ("idOnibus"),
  FOREIGN KEY ("idPagamento") REFERENCES "Pagamento" ("idPagamento")
);

CREATE TABLE IF NOT EXISTS "Destino" (
  "idDestino" INTEGER PRIMARY KEY AUTOINCREMENT,
  "nome" TEXT,
  "idEndereco" INTEGER,
  FOREIGN KEY ("idEndereco") REFERENCES "Endereco" ("IdEndereco")
);

CREATE TABLE IF NOT EXISTS "Onibus" (
  "idOnibus" INTEGER PRIMARY KEY AUTOINCREMENT,
  "placa" TEXT,
  "modelo" TEXT NOT NULL,
  "empresa" TEXT NOT NULL,
  "tipo" TEXT NOT NULL
);
