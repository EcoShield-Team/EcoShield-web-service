-- Roles
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('USUARIO', 'Rol por defecto de usuarios');
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('ADMIN', 'Rol por defecto para admins');

-- Usuarios
INSERT INTO usuario (rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro) VALUES (1, 'Gerardo Chávez', 'gerardo@gmail.com', '123456', 'ACTIVO', NULL, 'PERU', NOW());

INSERT INTO usuario (rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro) VALUES (1, 'María López', 'maria@gmail.com', 'abc123', 'ACTIVO', NULL, 'PERU', NOW());

INSERT INTO usuario (rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro) VALUES (1, 'Carlos Pérez', 'carlos@gmail.com', 'pass123', 'ACTIVO', NULL, 'CHILE', NOW());

INSERT INTO usuario (rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro) VALUES (2, 'Ana Torres', 'ana.admin@gmail.com', 'admin123', 'ACTIVO', NULL, 'PERU', NOW());

INSERT INTO usuario (rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro) VALUES (1, 'Luis Fernández', 'luis@gmail.com', 'qwerty', 'INACTIVO', NULL, 'ARGENTINA', NOW());

-- Plagas de ejemplo
INSERT INTO plaga (plaganombre, plaganombrecientifico, plagatipo,plagadescripcion, plagasintomas, plagatratamiento,plagacausas, plagaprevenciones, plagafoto,temporada, severidad) VALUES('Pulgón', 'Aphididae', 'INSECTO','Insecto pequeño que se alimenta de la savia de las plantas.','Hojas enrolladas, amarillentas y pegajosas por melaza.','Aplicación de jabón potásico o liberación de mariquitas.','Exceso de fertilización nitrogenada.','Rotación de cultivos y eliminación de maleza.','https://example.com/img/plagas/pulgon.jpg','PRIMAVERA', 'MODERADA'),(    'Mosca blanca', 'Trialeurodes vaporariorum', 'INSECTO',    'Pequeño insecto volador que afecta principalmente cultivos hortícolas.',    'Manchas amarillas en hojas, debilitamiento de la planta.',    'Uso de trampas cromáticas y control biológico con Encarsia formosa.',    'Ambientes cálidos y húmedos.',    'Ventilación adecuada en invernaderos.',    'https://example.com/img/plagas/mosca_blanca.jpg',    'VERANO', 'GRAVE')