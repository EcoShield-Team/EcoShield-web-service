-- ===== ROLES =====
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('ADMIN', 'Rol por defecto para administradores');
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('USER', 'Rol por defecto de usuarios');


-- ========================
-- 👤 TABLA: USUARIO                 contraseña: password / admin123 / usuario123
-- ========================
INSERT INTO usuario (usuarioid, rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro)
VALUES
    (1, 1, 'Gerardo Chávez', 'gerardomanuelrichard@gmail.com', '$2a$12$0eZ.w9rAEsY9bzl00.uLte4mE5.agTRH7T1qvrRE54BqNDVk6EE1K', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile1_ctpzyg.png', 'Perú', CURRENT_TIMESTAMP),
    (2, 1, 'Admin Global', 'admin@ecoshield.com', '$2a$12$zUO15GSNu.N6LrphgchVCeVAGsoXYI/wx9c896S7uxppJVeoqMckG', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile11_xrllds.jpg', 'Argentina', CURRENT_TIMESTAMP),
    (3, 2, 'Alexander Aquino', 'alexander@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile2_hrkoiy.jpg', 'Perú', CURRENT_TIMESTAMP),
    (4, 2, 'Camilo Parraga', 'camilopp810@gmail.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile3_uj5yw6.jpg', 'Ecuador', CURRENT_TIMESTAMP),
    (5, 2, 'Mauricio Mantilla', 'mauricio@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile10_oks6ue.avif', 'Colombia', CURRENT_TIMESTAMP),
    (6, 2, 'Marcelo Palomino', 'marcelo@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile4_ojix5z.jpg', 'Bolivia', CURRENT_TIMESTAMP),
    (7, 2, 'Hadisha Ludeña', 'hadisha@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile12_vusbwz.jpg', 'Perú', CURRENT_TIMESTAMP),
    (8, 2, 'Diego Castro', 'diego@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'BLOQUEADO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile7_mpxppf.jpg', 'Chile', CURRENT_TIMESTAMP),
    (9, 2, 'Laura Llanos', 'laura@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile6_olmnjd.jpg', 'México', CURRENT_TIMESTAMP),
    (10, 2, 'César Rodríguez', 'cesar@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile5_c41umt.jpg', 'Perú', CURRENT_TIMESTAMP),
    (11, 2, 'Gonzalo Morales', 'gonzalo@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile8_bfc0ao.jpg', 'Colombia', CURRENT_TIMESTAMP),
    (12, 2, 'Andrea Paredes', 'andrea@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996311/profile9_aibspy.jpg', 'Perú', CURRENT_TIMESTAMP);


-- ========================
-- 🪲 TABLA: PLAGA
-- ========================
INSERT INTO plaga (plagaid, plaganombre, plaganombrecientifico, plagatipo, plagadescripcion, plagasintomas, plagatratamiento, plagacausas, plagaprevenciones, plagafoto, temporada, severidad)
VALUES
    (1, 'Pulgón verde del duraznero', 'Myzus persicae', 'INSECTO',
     'Pequeño insecto chupador que se alimenta principalmente de la savia de hojas tiernas, brotes nuevos y tallos jóvenes. Su presencia debilita el crecimiento de la planta, favorece la transmisión de virus y genera condiciones propicias para el desarrollo de hongos como la fumagina debido a la melaza que excretan.',
     'Las hojas afectadas suelen enrollarse hacia adentro, presentar decoloración y volverse amarillentas. Se observa melaza pegajosa en la superficie de las hojas, además de la presencia frecuente de hormigas atraídas por esta sustancia. En infestaciones graves se evidencia retraso en el crecimiento, deformación de brotes y debilitamiento general de la planta.',
     'Para controlar la plaga se recomienda aplicar jabón potásico en los brotes afectados, utilizar extracto de neem como insecticida natural y realizar lavados suaves con agua para eliminar colonias visibles. En cultivos más sensibles puede complementarse con liberación de insectos benéficos como mariquitas o crisopas. Evitar el uso excesivo de insecticidas químicos para no eliminar depredadores naturales.',
     'Las principales causas de su aparición incluyen temperaturas templadas y ambientes húmedos. El exceso de fertilización nitrogenada genera brotes tiernos que favorecen su reproducción acelerada. También influyen la falta de ventilación en cultivos densos y la presencia de hormigas que protegen a los ',
     'Para prevenir la infestación se recomienda podar brotes dañados, reducir el exceso de nitrógeno en la fertilización, promover la presencia de enemigos naturales como mariquitas y crisopas, monitorear periódicamente las hojas jóvenes y evitar condiciones de exceso de humedad o hacinamiento vegetal. Mantener una adecuada ventilación y limpieza en el cultivo también ayuda a disminuir su aparición.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga1_dmtlo3.jpg', 'PRIMAVERA', 'LEVE'),

    (2, 'Mosca blanca', 'Bemisia tabaci', 'INSECTO',
     'Pequeño insecto volador de color blanco que se alimenta de la savia en el envés de las hojas. Se reproduce rápidamente en condiciones cálidas y puede transmitir diversos virus fitopatógenos. Su alimentación constante debilita notablemente el vigor de la planta y favorece la aparición de hongos como la fumagina debido a la melaza que excretan.',
     'Las hojas afectadas muestran un amarillamiento progresivo, pérdida de firmeza y caída prematura. Se observa una capa pegajosa de melaza en la superficie foliar, la cual facilita el desarrollo de manchas oscuras de fumagina. En infestaciones avanzadas se aprecia debilitamiento general, reducción del crecimiento y marchitez en horas de alta radiación.',
     'El control recomendado incluye el uso de trampas cromáticas amarillas para capturar adultos y monitorear la población. La liberación de Encarsia formosa, una avispa parásita natural de la mosca blanca, es altamente efectiva en el manejo biológico. También pueden aplicarse extractos naturales como neem o jabón potásico en el envés de las hojas, evitando pulverizar en horas de alta temperatura.',
     'Su aparición está asociada a climas cálidos y secos, invernaderos con poca ventilación y exceso de densidad de cultivo. Las plantas debilitadas por estrés hídrico o fertilización desequilibrada son más susceptibles. La presencia de malezas cercanas también contribuye al aumento de la población.',
     'Para prevenir su proliferación se recomienda mantener una fertilización equilibrada sin excesos de nitrógeno, controlar malezas que puedan servir de hospedero, mejorar la ventilación en cultivos densos y colocar trampas amarillas desde el inicio de la temporada. El monitoreo frecuente del envés de las hojas permite detectar la plaga de forma temprana y evitar brotes severos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga2_vpyz7k.jpg', 'VERANO', 'MODERADA'),

    (3, 'Ácaro rojo', 'Tetranychus urticae', 'ACARO',
     'Ácaro diminuto que causa decoloración y necrosis en hojas.',
     'Manchas amarillas, telarañas finas en el envés.',
     'Azufre micronizado o extractos de ajo.',
     'Alta temperatura y baja humedad.',
     'Mantener humedad adecuada y revisar el envés de hojas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998737/plaga3_ta4wyh.jpg', 'VERANO', 'GRAVE'),

    (4, 'Gusano cogollero', 'Spodoptera frugiperda', 'INSECTO',
     'Larva altamente voraz que ataca principalmente el cogollo de cultivos como maíz, sorgo y otros gramíneos. Su alimentación directa sobre hojas y tejidos internos provoca daños graves que afectan el desarrollo de la planta. Es una de las plagas agrícolas más agresivas debido a su rápida reproducción, movilidad y capacidad de generar resistencia a ciertos insecticidas.',
     'Los síntomas incluyen hojas perforadas con bordes irregulares, presencia de excrementos oscuros dentro del cogollo y destrucción progresiva del tejido central de la planta. En casos severos, el cogollo puede quedar completamente deshecho, provocando baja emergencia de nuevas hojas, retraso en el crecimiento y disminución significativa del rendimiento del cultivo.',
     'Para su control se recomienda el uso de Bacillus thuringiensis, un bioinsecticida selectivo eficaz contra larvas jóvenes. También se puede implementar manejo biológico con parasitoides y depredadores naturales. En situaciones críticas se puede complementar con aplicaciones focalizadas de productos reguladores de crecimiento, evitando tratamientos químicos repetitivos que favorezcan la resistencia del insecto.',
     'Las condiciones que favorecen su aparición incluyen ambientes con alta humedad, disponibilidad constante de hospederos y prácticas agrícolas basadas en monocultivo. La falta de rotación de cultivos y residuos vegetales no manejados adecuadamente también incrementan la presión poblacional de la plaga.',
     'Como medidas preventivas se recomienda realizar rotación de cultivos para interrumpir el ciclo biológico de la plaga, instalar trampas de feromonas para monitoreo temprano, eliminar restos de cosecha que puedan servir de refugio y fomentar la presencia de enemigos naturales. Una siembra escalonada y un monitoreo constante del cogollo ayudan a detectar la plaga en sus primeras fases, donde el control es más efectivo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998734/plaga4_oicvmz.jpg', 'TODO_EL_AÑO', 'GRAVE'),

    (5, 'Nematodo de las raíces', 'Meloidogyne spp.', 'NEMATODO',
     'Parásito microscópico que vive en el suelo y afecta directamente las raíces de una amplia variedad de cultivos. Al alimentarse, induce la formación de agallas o deformaciones que interfieren con la absorción de agua y nutrientes. Su presencia puede pasar desapercibida en etapas tempranas, pero causa pérdidas significativas al debilitar la planta y reducir su productividad.',
     'El síntoma más característico es la aparición de nódulos o agallas en las raíces, las cuales pueden variar en tamaño dependiendo del nivel de infestación. Las plantas suelen mostrar crecimiento reducido, marchitez durante horas de calor, clorosis en hojas y menor desarrollo radicular. En casos severos, las plantas quedan atrofiadas y el rendimiento final disminuye drásticamente.',
     'El control incluye la solarización del suelo, una técnica que utiliza el calor del sol para reducir la población de nematodos en la capa superficial. También se recomienda el uso de extractos de neem, que actúan como nematicida natural y ayudan a disminuir la infestación. La incorporación de materia orgánica y microorganismos benéficos también favorece un ambiente menos propicio para el desarrollo de la plaga.',
     'Las principales causas de infestación están asociadas a suelos mal drenados, contaminados previamente o con historial de cultivos sensibles sin manejo adecuado. El movimiento de herramientas, agua de riego o plántulas infectadas puede contribuir a la dispersión del nematodo dentro del campo.',
     'Para prevenir su aparición se recomienda utilizar semillas y plantines certificados libres de nematodos, realizar rotación de cultivos con especies menos susceptibles, mejorar el drenaje del suelo y evitar prácticas que favorezcan la compactación. El monitoreo constante del sistema radicular y la desinfección de herramientas agrícolas también ayudan a reducir el riesgo de reinfestación.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998734/plaga5_uq3k15.jpg', 'VERANO', 'MODERADA'),

    (6, 'Trips del tomate', 'Frankliniella occidentalis', 'INSECTO',
     'Pequeño insecto alargado que se alimenta al perforar las células de hojas, brotes tiernos y flores para succionar su contenido. Su actividad provoca pérdida de clorofila, debilitamiento del tejido vegetal y daños estéticos severos. Además, es un vector importante de virus como el TSWV, lo que lo convierte en una plaga de alto riesgo para cultivos hortícolas.',
     'Los daños característicos incluyen deformaciones en hojas y brotes jóvenes, apariencia plateada o bronceada en el envés de las hojas debido al raspado del tejido, presencia de puntos negros de excremento y caída prematura de flores. En infestaciones avanzadas se observa reducción en el cuajado de frutos y plantas con desarrollo limitado.',
     'El control recomendado incluye la aplicación de jabón potásico directamente en zonas afectadas para reducir la población y el uso de extractos naturales como neem. Mantener niveles adecuados de humedad relativa puede dificultar la reproducción del insecto, ya que los trips prosperan en condiciones secas. En cultivos bajo invernadero también es útil la instalación de mallas anti-insectos y trampas adhesivas azules para monitoreo.',
     'Su aparición es favorecida por ambientes calurosos, secos y con alta densidad de siembra. El estrés hídrico y la acumulación de restos vegetales también contribuyen a aumentar su presencia. Los invernaderos con ventilación deficiente o manejo inadecuado suelen registrar brotes más frecuentes.',
     'Para prevenir la infestación se recomienda desinfectar herramientas de poda, eliminar restos vegetales que puedan servir como refugio, evitar la compactación de plantas mediante un espaciamiento adecuado y mantener buena ventilación en invernaderos. La inspección frecuente de flores y brotes jóvenes permite detectar la plaga de forma temprana, facilitando un control más efectivo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga6_kvrq7k.jpg', 'PRIMAVERA', 'LEVE'),

    (7, 'Minador de hojas', 'Liriomyza trifolii', 'INSECTO',
     'Las larvas de este díptero se alimentan del tejido interno de las hojas, creando túneles o galerías que dificultan la fotosíntesis y debilitan el desarrollo de la planta. Aunque los adultos son pequeños mosquitos, el verdadero daño lo ocasionan las larvas al excavar dentro del limbo foliar. Esta plaga afecta una amplia variedad de cultivos hortícolas y ornamentales.',
     'El síntoma más visible son las líneas serpenteantes de color claro que se extienden por la superficie de las hojas, resultado del recorrido de las larvas. En infestaciones avanzadas se observa necrosis alrededor de las galerías, debilitamiento del tejido, caída prematura de hojas y reducción del vigor general de la planta. Las hojas fuertemente dañadas pueden secarse desde las puntas hacia el centro.',
     'El manejo recomendado incluye la eliminación y poda de hojas afectadas para cortar el ciclo biológico del insecto. También se pueden utilizar trampas adhesivas amarillas para capturar adultos y monitorear su presencia. El uso de control biológico mediante parasitoides como Diglyphus isaea es una estrategia efectiva para mantener la población en niveles bajos.',
     'Su presencia se ve favorecida por temperaturas elevadas, riego excesivo que genera hojas tiernas más susceptibles y ambientes protegidos como invernaderos. La falta de ventilación y el crecimiento denso también facilitan su establecimiento y dispersión.',
     'Para prevenir la infestación se recomienda realizar rotación de cultivos, mantener un riego equilibrado evitando excesos, fomentar la presencia de insectos benéficos y realizar un monitoreo constante especialmente en épocas cálidas. Retirar restos vegetales y mantener la higiene del cultivo ayuda a reducir el riesgo de colonización.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga7_vi3xqb.jpg', 'OTOÑO', 'LEVE'),

    (8, 'Cochinilla algodonosa', 'Planococcus citri', 'INSECTO',
     'Insecto pequeño y ovalado cubierto por una masa cerosa blanca similar al algodón. Se alimenta de la savia de diversas plantas, especialmente cítricos y ornamentales, debilitando su crecimiento y favoreciendo el desarrollo de hongos como la fumagina debido a la melaza que excreta. Es una plaga muy persistente y puede esconderse en uniones de hojas, tallos y frutos.',
     'Los síntomas incluyen pérdida notable de vigor, hojas deformadas o amarillentas, presencia de masas algodonosas en nervaduras y brotes tiernos, así como melaza brillante en la superficie de las hojas. En infestaciones intensas puede observarse fumagina negra, caída de hojas y reducción significativa de la producción de frutos.',
     'El control recomendado incluye el uso de enemigos naturales como Cryptolaemus montrouzieri, un depredador altamente efectivo contra cochinillas. También es útil la aplicación de jabón potásico o aceite mineral para desprender manualmente las colonias, especialmente en fases iniciales. En plantas pequeñas puede realizarse limpieza directa con algodón humedecido en alcohol para eliminar los focos visibles.',
     'Las cochinillas proliferan principalmente en climas cálidos y secos, especialmente en ambientes protegidos como invernaderos. El exceso de sombra, la falta de ventilación y la presencia de hormigas que protegen las colonias a cambio de melaza favorecen su expansión. Plantas debilitadas también resultan más vulnerables.',
     'Para prevenir su aparición es recomendable mantener buena ventilación en el cultivo, evitar acumulación de humedad en zonas internas de la planta, eliminar restos vegetales y controlar poblaciones de hormigas. Revisar periódicamente brotes tiernos y zonas ocultas permite detectar la plaga a tiempo y evitar su propagación.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga8_bu7mtx.jpg', 'TODO_EL_AÑO', 'MODERADA'),

    (9, 'Araña amarilla', 'Polyphagotarsonemus latus', 'ACARO',
     'Ácaro diminuto de color amarillento que se alimenta del contenido celular de hojas y brotes tiernos. Su actividad provoca deformaciones severas en tejidos jóvenes y altera el desarrollo normal de la planta. Aunque es difícil de ver a simple vista debido a su tamaño, sus daños son muy característicos y pueden afectar una amplia variedad de cultivos hortícolas y ornamentales.',
     'Los síntomas incluyen hojas enrolladas hacia abajo, clorosis progresiva, endurecimiento o engrosamiento anormal del tejido y brotes que detienen su crecimiento. En algunos cultivos las hojas toman un aspecto rugoso o brillante debido al daño celular. En infestaciones avanzadas pueden observarse necrosis en puntas y deformación severa en flores y nuevas hojas.',
     'El manejo recomendado incluye la aplicación de extracto de ajo o jabón insecticida para reducir la población en zonas afectadas. También puede emplearse aceite mineral o extractos naturales como neem, especialmente en brotes jóvenes donde el ácaro se establece con mayor facilidad. Mantener la humedad ambiental moderada puede reducir su proliferación, ya que prefieren condiciones secas y cálidas.',
     'Su aparición se ve favorecida por altas temperaturas combinadas con baja humedad relativa, especialmente en cultivos bajo invernadero o en temporadas secas. El estrés hídrico y la presencia de tejido tierno en crecimiento continuo también facilitan su establecimiento. Las plantas debilitadas responden peor al ataque del ácaro.',
     'Como medidas preventivas se recomienda mantener un riego adecuado evitando periodos prolongados de sequedad, fomentar el uso de biocontroladores como ácaros depredadores y realizar inspecciones frecuentes en brotes nuevos. Retirar tejido afectado en fases iniciales y mantener buena ventilación del cultivo ayuda a disminuir la presión de la plaga.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998737/plaga9_ot5ian.jpg', 'VERANO', 'LEVE'),

    (10, 'Escarabajo del pepino', 'Diabrotica balteata', 'INSECTO',
     'Escarabajo pequeño de color verde metálico o amarillo con patrones oscuros, conocido por alimentarse de hojas, tallos y flores de plantas cucurbitáceas como pepino, melón y calabaza. Además de los daños directos por defoliación, es un importante transmisor de virus y bacterias que afectan severamente el rendimiento de los cultivos.',
     'Los síntomas incluyen agujeros irregulares en hojas, bordes mordidos, daños en tallos jóvenes y flores parcialmente devoradas. Las plantas infestadas pueden mostrar retraso en el crecimiento, menor cuajado de frutos y signos de marchitez debido a la transmisión de patógenos. En infestaciones graves, los brotes tiernos pueden ser completamente consumidos.',
     'El manejo recomendado incluye el uso de trampas amarillas o cebos atrayentes para capturar adultos, así como aplicaciones de Beauveria bassiana, un hongo entomopatógeno eficaz contra escarabajos. También pueden emplearse coberturas flotantes para proteger plantas jóvenes en etapas críticas. El control temprano es clave debido a la capacidad del insecto para transmitir enfermedades.',
     'La presencia del escarabajo se ve favorecida por altas temperaturas, acumulación de malezas cercanas que sirven como refugio y exceso de humedad en el follaje. Los campos con manejo deficiente de residuos de cosecha suelen registrar poblaciones más elevadas debido a la presencia de huevos y larvas en el suelo.'',',
     'Las medidas preventivas incluyen la rotación de cultivos, la eliminación de residuos vegetales después de la cosecha, el control de malezas dentro y alrededor del cultivo y el monitoreo constante durante las primeras semanas de crecimiento de la planta. Mantener el campo limpio y bien ventilado reduce significativamente la incidencia de esta plaga.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga10_g87yzj.jpg', 'PRIMAVERA', 'MODERADA');

-- 🦟 1. Pulgón verde del duraznero
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (1, 'pulgones verdes'),
                                              (1, 'pulgón del melocotonero'),
                                              (1, 'pulgón del durazno'),
                                              (1, 'pulgón común'),
                                              (1, 'green peach aphid'),
                                              (1, 'aphid myzus persicae'),
                                              (1, 'greenfly'),
                                              (1, 'peach aphid'),
                                              (1, 'áfido verde'),
                                              (1, 'insecto chupador'),
                                              (1, 'plaga de hojas tiernas'),
                                              (1, 'myzus persicae'),
                                              (1, 'pulgón agrícola'),
                                              (1, 'áfido del pimiento'),
                                              (1, 'áfido del tomate');

-- 🪰 2. Mosca blanca
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (2, 'mosquita blanca'),
                                              (2, 'whitefly'),
                                              (2, 'bemisia'),
                                              (2, 'aleurodidae'),
                                              (2, 'mosca blanca del tomate'),
                                              (2, 'aleurodes vaporariorum'),
                                              (2, 'greenhouse whitefly'),
                                              (2, 'mosca blanca de los invernaderos'),
                                              (2, 'whiteflies'),
                                              (2, 'plaga chupadora'),
                                              (2, 'mosca blanca común'),
                                              (2, 'mosca blanca del pepino'),
                                              (2, 'mosca blanca del frijol'),
                                              (2, 'pequeña mosca blanca'),
                                              (2, 'mosca blanca tropical');

-- 🕷️ 3. Ácaro rojo
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (3, 'spider mite'),
                                              (3, 'red spider mite'),
                                              (3, 'ácaro del tomate'),
                                              (3, 'ácaro de dos manchas'),
                                              (3, 'ácaro rojo común'),
                                              (3, 'tetranychus'),
                                              (3, 'tetranychus urticae'),
                                              (3, 'mite pest'),
                                              (3, 'plaga arácnida'),
                                              (3, 'ácaros fitófagos'),
                                              (3, 'ácaro telarañero'),
                                              (3, 'spotted spider mite'),
                                              (3, 'ácaro rojo de las hojas'),
                                              (3, 'two-spotted spider mite'),
                                              (3, 'plaga del algodón');

-- 🐛 4. Gusano cogollero
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (4, 'fall armyworm'),
                                              (4, 'spodoptera frugiperda'),
                                              (4, 'gusano del maíz'),
                                              (4, 'oruga cogollera'),
                                              (4, 'cogollero del maíz'),
                                              (4, 'armyworm'),
                                              (4, 'gusano del sorgo'),
                                              (4, 'gusano de las hojas'),
                                              (4, 'spodoptera'),
                                              (4, 'cogollero tropical'),
                                              (4, 'gusano barrenador'),
                                              (4, 'gusano devorador de hojas'),
                                              (4, 'larva defoliadora'),
                                              (4, 'maize armyworm'),
                                              (4, 'oruga del maíz');

-- 🧬 5. Nematodo de las raíces
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (5, 'root-knot nematode'),
                                              (5, 'nematodo agallador'),
                                              (5, 'nematodo del suelo'),
                                              (5, 'meloidogyne'),
                                              (5, 'nemátodos agalladores'),
                                              (5, 'root nematode'),
                                              (5, 'nematodo de las plantas'),
                                              (5, 'nemátodo parásito'),
                                              (5, 'nematodo radicular'),
                                              (5, 'nemátodo dañino'),
                                              (5, 'plaga subterránea'),
                                              (5, 'gall nematode'),
                                              (5, 'root knot'),
                                              (5, 'nemátodo de raíces'),
                                              (5, 'meloidogyne incognita');

-- 🌸 6. Trips del tomate
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (6, 'thrips'),
                                              (6, 'frankliniella occidentalis'),
                                              (6, 'trips de las flores'),
                                              (6, 'western flower thrips'),
                                              (6, 'pequeños trips'),
                                              (6, 'insecto trips'),
                                              (6, 'plaga de flores'),
                                              (6, 'trips del invernadero'),
                                              (6, 'flower thrips'),
                                              (6, 'tomato thrips'),
                                              (6, 'frankliniella'),
                                              (6, 'trips de los pétalos'),
                                              (6, 'trips común'),
                                              (6, 'trips amarillo'),
                                              (6, 'trips de hojas');

-- 🍃 7. Minador de hojas
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (7, 'leaf miner'),
                                              (7, 'minador'),
                                              (7, 'minador americano'),
                                              (7, 'liriomyza'),
                                              (7, 'minador de hojas de tomate'),
                                              (7, 'liriomyza trifolii'),
                                              (7, 'minador del frijol'),
                                              (7, 'minador del melón'),
                                              (7, 'plaga de hojas'),
                                              (7, 'mining fly'),
                                              (7, 'hoja minada'),
                                              (7, 'larva minadora'),
                                              (7, 'minador común'),
                                              (7, 'leaf-mining fly'),
                                              (7, 'minador vegetal');

-- 🍊 8. Cochinilla algodonosa
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (8, 'cochinilla blanca'),
                                              (8, 'cochinilla algodonosa'),
                                              (8, 'planococcus citri'),
                                              (8, 'mealybug'),
                                              (8, 'cochinilla de los cítricos'),
                                              (8, 'citrus mealybug'),
                                              (8, 'plaga cerosa'),
                                              (8, 'cochinilla harinosa'),
                                              (8, 'insecto algodonoso'),
                                              (8, 'cochinilla del mango'),
                                              (8, 'white mealybug'),
                                              (8, 'plaga del limón'),
                                              (8, 'mealy insect'),
                                              (8, 'plaga algodonosa'),
                                              (8, 'cochinilla adherida');

-- 🕸️ 9. Araña amarilla
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (9, 'broad mite'),
                                              (9, 'ácaro amarillo'),
                                              (9, 'tarsonémido'),
                                              (9, 'yellow mite'),
                                              (9, 'polyphagotarsonemus latus'),
                                              (9, 'ácaro ancho'),
                                              (9, 'ácaro del chile'),
                                              (9, 'broad spider mite'),
                                              (9, 'ácaro de la papaya'),
                                              (9, 'mite amarillo'),
                                              (9, 'plaga microscópica'),
                                              (9, 'ácaro de hoja'),
                                              (9, 'ácaro de hortalizas'),
                                              (9, 'yellow broad mite'),
                                              (9, 'tarsonemid mite');

-- 🪲 10. Escarabajo del pepino
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (10, 'cucumber beetle'),
                                              (10, 'diabrotica'),
                                              (10, 'escarabajo rayado del pepino'),
                                              (10, 'beetle pest cucumber'),
                                              (10, 'escarabajo del pepino'),
                                              (10, 'diabrotica undecimpunctata'),
                                              (10, 'striped cucumber beetle'),
                                              (10, 'spotted cucumber beetle'),
                                              (10, 'escarabajo amarillo y negro'),
                                              (10, 'beetle plaga de pepino'),
                                              (10, 'diabrotica balteata'),
                                              (10, 'escarabajo de las cucurbitáceas'),
                                              (10, 'cucumber pest'),
                                              (10, 'plaga del pepino'),
                                              (10, 'beetle larvae cucumber');
-- ========================
-- 🌱 TABLA: ENFERMEDAD
-- ========================
INSERT INTO enfermedad (enfermedadid, enfermedadnombre, enfermedadnombrecientifico, enfermedadtipo, enfermedaddescripcion, enfermedadsintomas, enfermedadtratamiento, enfermedadcausas, enfermedadprevenciones, enfermedadfoto, temporada, severidad)
VALUES
    (1, 'Tizón tardío del tomate', 'Phytophthora infestans', 'HONGO',
     'Enfermedad altamente destructiva causada por un oomiceto que afecta hojas, tallos y frutos del tomate. Produce necrosis rápida del tejido y se propaga con facilidad en ambientes húmedos y fríos. Es una de las enfermedades más agresivas en solanáceas y puede causar pérdidas totales si no se maneja a tiempo.',
     'Los síntomas iniciales incluyen manchas irregulares de color verde oscuro o parduzco en las hojas, que rápidamente se extienden y adoptan un borde amarillento. Con el avance de la enfermedad, aparece un moho blanco o grisáceo en el envés de las hojas durante periodos de alta humedad. Los tallos pueden presentar lesiones oscuras y los frutos desarrollan manchas firmes, hundidas y de color café, que progresan hasta generar pudrición.',
     'El control recomendado incluye aplicaciones preventivas de productos a base de cobre, que ayudan a ralentizar la propagación del patógeno. El uso de microorganismos benéficos como Trichoderma harzianum también es eficaz para inhibir el desarrollo del oomiceto en el suelo y la superficie vegetal. Es importante iniciar los tratamientos ante las primeras señales o cuando las condiciones ambientales sean favorables para el tizón.',
     'Las principales causas están asociadas a alta humedad relativa, lluvias frecuentes, neblina y bajas temperaturas nocturnas. La presencia de agua libre en las hojas favorece la germinación de las esporas. El uso de semillas infectadas, riego por aspersión durante la noche y la falta de ventilación también incrementan el riesgo.',
     'Para prevenir la enfermedad se recomienda rotar cultivos con especies no hospedantes, eliminar y destruir los restos vegetales infectados, evitar el riego sobre el follaje, mejorar la ventilación en invernaderos y utilizar variedades tolerantes. Un monitoreo constante del cultivo y una buena separación entre plantas ayudan a reducir la humedad interna y dificultan la propagación del patógeno.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999177/enf1_mbcgfd.jpg', 'INVIERNO', 'GRAVE'),

    (2, 'Roya del café', 'Hemileia vastatrix', 'HONGO',
     'Hongo altamente agresivo que ataca las hojas del cafeto y es responsable de grandes pérdidas en la producción cafetalera a nivel mundial. Afecta principalmente las hojas maduras, reduciendo la capacidad fotosintética de la planta y debilitando su desarrollo general. La enfermedad puede dispersarse rápidamente bajo condiciones favorables.',
     'Los síntomas comienzan como pequeñas manchas cloróticas en el haz de las hojas, que luego se correlacionan con la aparición de pústulas de color amarillo anaranjado en el envés, características de la roya. Con el avance de la enfermedad, las hojas se secan, caen prematuramente y la planta reduce notablemente su capacidad de producir frutos. En ataques severos, el árbol queda casi defoliado.',
     'El tratamiento incluye el uso de fungicidas naturales o biocontroladores como cepas de Bacillus y Trichoderma, que ayudan a reducir la carga del hongo. También se pueden implementar aplicaciones preventivas con extractos vegetales antifúngicos. El manejo integrado que combina sombra adecuada, fertilización equilibrada y monitoreo constante mejora significativamente el control.',
     'Las principales causas de su aparición están asociadas a lluvias frecuentes, humedad constante, alta densidad de siembra y temperaturas moderadas. La falta de ventilación en cultivos sombreados también incrementa la probabilidad de infección. Las esporas del hongo se dispersan fácilmente con el viento y las salpicaduras de lluvia.',
     'Para prevenir la enfermedad se recomienda realizar podas para mejorar la ventilación del cultivo, eliminar hojas infectadas, escoger variedades resistentes y mantener una nutrición adecuada que fortalezca el árbol. Las renovaciones periódicas de cafetales viejos y el monitoreo constante durante épocas húmedas son claves para evitar brotes severos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf2_ukmwv0.jpg', 'OTOÑO', 'GRAVE'),

    (3, 'Marchitez bacteriana del plátano', 'Ralstonia solanacearum', 'BACTERIA',
     'Enfermedad bacteriana altamente destructiva que afecta al plátano y otras musáceas. Ataca el sistema vascular de la planta, bloqueando el transporte de agua y nutrientes, lo que provoca una marchitez progresiva y finalmente la muerte de la planta. Es una de las enfermedades más temidas en zonas tropicales por su rápida diseminación y dificultad de erradicación.',
     'Los síntomas incluyen hojas caídas que permanecen verdes al inicio, marchitez repentina de las hojas más jóvenes, tallos que presentan zonas internas oscuras o ennegrecidas y exudados bacterianos en cortes frescos del pseudotallo. Las plantas afectadas suelen colapsar rápidamente y muestran decoloración vascular café o rojiza en el interior del tallo.',
     'El manejo recomendado incluye la desinfección del suelo en áreas infectadas, el uso de agentes de control biológico para reducir la población bacteriana y la eliminación inmediata de plantas enfermas para evitar la dispersión. Es fundamental desinfectar herramientas de corte, ya que la bacteria puede transmitirse fácilmente a través de ellas.',
     'Las causas principales están asociadas a altas temperaturas, uso de suelos previamente contaminados, riego o agua de escorrentía que transporta la bacteria y manejo inadecuado de herramientas agrícolas. La presencia de nematodos y heridas en raíces también facilita la entrada del patógeno.',
     'Para prevenir la enfermedad se recomienda utilizar material de siembra certificado libre de patógenos, rotar cultivos con especies no hospedantes, mejorar el drenaje del suelo y evitar el uso de agua contaminada. La vigilancia constante del cultivo y la eliminación de restos infectados reducen significativamente el riesgo de brotes.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf3_qrtnvz.jpg', 'VERANO', 'GRAVE'),

    (4, 'Virus del mosaico del pepino', 'Cucumber mosaic virus', 'VIRUS',
     'Virus altamente polífago transmitido principalmente por pulgones, capaz de infectar una amplia variedad de hortalizas y plantas ornamentales. Afecta el desarrollo foliar y reduce drásticamente la productividad, ya que altera los procesos fisiológicos de la planta y limita la formación adecuada de frutos.',
     'Los síntomas más característicos incluyen patrones de mosaico amarillo o verde claro en las hojas, distorsión del limbo foliar, reducción del tamaño de las hojas y crecimiento desigual. En plantas jóvenes, la infección provoca retraso notable en el desarrollo y brotes deformados. Los frutos pueden presentar manchas irregulares, deformaciones y pérdida de calidad comercial.',
     'No existen tratamientos curativos para este virus, por lo que la principal medida es la eliminación inmediata de plantas afectadas para evitar la propagación. El manejo se basa en reducir la presencia de pulgones mediante control biológico, aplicaciones suaves de jabón potásico o aceites hortícolas, y mantener condiciones del cultivo que desfavorezcan al vector.',
     'Las principales causas de su aparición están relacionadas con la presencia de insectos vectores, especialmente pulgones que transmiten el virus al alimentarse. Factores como altas densidades de cultivo, falta de monitoreo y presencia de malezas hospedantes facilitan su circulación dentro del campo. El uso de plántulas infectadas también puede iniciar brotes tempranos.',
     'Las medidas preventivas incluyen el uso de semillas certificadas libres de virus, la eliminación de malezas y plantas voluntarias que puedan actuar como reservorio, el monitoreo constante de población de pulgones y la implementación de barreras físicas o trampas cromáticas. Mantener buenas prácticas de higiene agrícola y un manejo adecuado del vector es clave para evitar la infección.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf4_vgrwsp.jpg', 'PRIMAVERA', 'MODERADA'),

    (5, 'Mancha negra de la rosa', 'Diplocarpon rosae', 'HONGO',
     'Hongo foliar muy común en rosales que provoca la aparición de manchas oscuras y una defoliación prematura característica. Afecta principalmente las hojas, reduciendo la capacidad fotosintética de la planta y debilitándola progresivamente. En condiciones favorables puede propagarse rápidamente dentro del jardín o cultivo ornamental.',
     'Los síntomas incluyen manchas negras circulares o irregulares con bordes difusos que aparecen en la superficie de las hojas. Con el avance de la enfermedad, el tejido alrededor de las manchas se torna amarillo, las hojas se debilitan y caen prematuramente. En infestaciones severas, la planta puede quedar casi defoliada, afectando su vigor y floración.',
     'El tratamiento recomendado incluye la poda de hojas y tallos afectados para eliminar el inóculo del hongo, así como aplicaciones preventivas o curativas de productos a base de cobre. También pueden emplearse fungicidas orgánicos o extractos vegetales antifúngicos para reducir la incidencia, especialmente en temporadas húmedas. Es fundamental retirar y desechar el material infectado lejos del cultivo.',
     'La enfermedad se desarrolla con mayor facilidad en ambientes con exceso de humedad, lluvia frecuente, riego sobre el follaje y poca aireación entre plantas. La humedad persistente en hojas favorece la germinación de las esporas y acelera la propagación del patógeno.',
     'Para prevenir su aparición es importante mejorar la ventilación del rosal mediante una poda adecuada, evitar el riego directo sobre las hojas, espaciar correctamente las plantas y mantener un riego controlado. La eliminación temprana de hojas caídas y la aplicación periódica de tratamientos preventivos reduce significativamente el riesgo de infección.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf5_wv1uvr.jpg', 'PRIMAVERA', 'LEVE'),

    (6, 'Pudrición de raíz', 'Fusarium oxysporum', 'HONGO',
     'Hongo del suelo que invade el sistema radicular de las plantas, destruyendo los tejidos internos y bloqueando la absorción de agua y nutrientes. Afecta a numerosas especies hortícolas, ornamentales y frutales, causando debilitamiento progresivo y, en casos severos, la muerte total de la planta. Es persistente en el suelo y puede mantenerse activo durante años.',
     'Los síntomas incluyen amarillamiento progresivo de las hojas, marchitez incluso cuando el suelo permanece húmedo, retraso en el crecimiento y pérdida de vigor general. Al examinar las raíces, se observa pudrición, coloración marrón oscura, textura blanda y mal olor. Las plantas pueden mostrar necrosis en tallos basales debido al avance del patógeno hacia la parte aérea.',
     'El tratamiento recomendado consiste en la aplicación de Trichoderma, un hongo benéfico que compite directamente con Fusarium y ayuda a reducir su presencia en el suelo. También es fundamental mejorar el drenaje del sustrato para evitar la acumulación de humedad, así como eliminar plantas severamente afectadas para reducir la propagación. La desinfección de herramientas y el uso de sustratos sanos fortalecen el manejo.',
     'Las causas principales están relacionadas con suelos mal drenados, encharcamientos frecuentes, materia orgánica en descomposición y heridas en raíces que facilitan la entrada del hongo. Las condiciones cálidas y húmedas aceleran el proceso de infección y favorecen el crecimiento del patógeno.',
     'Para prevenir esta enfermedad se recomienda evitar riegos excesivos, utilizar sustratos con buen drenaje, desinfectar herramientas y contenedores, y no reutilizar tierra contaminada. La rotación de cultivos y la incorporación de microorganismos benéficos ayudan a mantener un suelo más equilibrado y menos propenso a brotes de Fusarium.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf6_vetcgi.jpg', 'TODO_EL_AÑO', 'GRAVE'),

    (7, 'Agalla bacteriana', 'Agrobacterium tumefaciens', 'BACTERIA',
     'Bacteria del suelo que infecta plantas a través de heridas en raíces, tallos o zonas cercanas al cuello. Produce tumores o agallas característicos al insertar material genético propio dentro de las células vegetales, provocando un crecimiento descontrolado del tejido. Afecta una gran variedad de especies, incluyendo frutales, ornamentales y cultivos hortícolas.',
     'Los síntomas principales son protuberancias o agallas de diferentes tamaños ubicadas en el cuello de la raíz, los tallos o las raíces laterales. Estas estructuras comienzan como bultos pequeños y claros, pero con el tiempo se vuelven más grandes, rugosos y de color oscuro. Las plantas infectadas muestran menor vigor, crecimiento lento, hojas pequeñas y reducción en la producción debido a la dificultad para absorber nutrientes y agua.',
     'El manejo recomendado incluye la desinfección constante de herramientas de poda y trasplante, ya que la bacteria se transmite fácilmente a través de objetos contaminados. También se debe evitar el movimiento de suelo infectado y eliminar plantas gravemente afectadas para reducir la fuente de inóculo. En viveros y huertos comerciales, el uso de productos biológicos que compiten con Agrobacterium puede ayudar a limitar su proliferación.',
     'La principal causa de infección es la contaminación cruzada entre plantas sanas y enfermas mediante herramientas, maquinaria agrícola, agua contaminada o suelo proveniente de áreas infectadas. Las heridas en raíces causadas por trasplantes, insectos o labores mecánicas facilitan enormemente la entrada de la bacteria.',
     'Para prevenir la enfermedad se recomienda realizar rotación de cultivos con especies menos susceptibles, mantener estrictas medidas de control sanitario, desinfectar herramientas antes de cada uso, evitar heridas innecesarias en la planta y trabajar con plántulas certificadas libres de patógenos. Un manejo adecuado del suelo y la eliminación de plantas afectadas disminuyen significativamente la incidencia.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999179/enf7_vxruvk.jpg', 'VERANO', 'LEVE'),

    (8, 'Mildiu polvoriento', 'Erysiphe cichoracearum', 'HONGO',
     'Enfermedad fúngica común en numerosos cultivos y plantas ornamentales, caracterizada por la formación de un polvo blanco o grisáceo sobre la superficie de hojas, tallos y a veces flores. El hongo se desarrolla principalmente en la parte externa de los tejidos, pero afecta los procesos fisiológicos de la planta, reduciendo su vigor y capacidad de crecimiento.',
     'Los síntomas incluyen la aparición de una capa blanquecina similar a talco en hojas y tallos, deformación del follaje, arrugamiento de hojas jóvenes, debilitamiento de los brotes y caída prematura del follaje en casos severos. Las hojas afectadas pueden amarillear, volverse quebradizas y presentar una marcada reducción en su tamaño y turgencia.',
     'El tratamiento recomendado consiste en la aplicación de azufre micronizado, uno de los fungicidas más eficaces y tradicionales para el control del mildiu polvoriento. También pueden utilizarse preparados orgánicos como bicarbonato potásico o extractos vegetales con propiedades antifúngicas. Las aplicaciones deben realizarse de manera preventiva o en las primeras fases de la infección para maximizar su efectividad.',
     'Las causas principales incluyen ambientes con alta humedad relativa combinada con poca ventilación y baja exposición solar. El hongo prospera en condiciones de sombreado, hacinamiento de plantas y climas templados, aunque puede desarrollarse incluso en condiciones moderadamente secas si existe suficiente humedad en las hojas.',
     'Para prevenir la enfermedad es importante evitar el hacinamiento mediante un adecuado espaciamiento entre plantas, mejorar la circulación de aire, evitar riego por aspersión en horas frías y promover una correcta poda que permita la entrada de luz. Retirar hojas infectadas y monitorear zonas sombreadas ayuda a reducir el riesgo de aparición y propagación del hongo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf8_biqrrx.jpg', 'PRIMAVERA', 'MODERADA'),

    (9, 'Virus del enrollamiento de la hoja del tomate', 'Tomato leaf curl virus', 'VIRUS',
     'Virus transmitido principalmente por la mosca blanca, que afecta de manera severa el crecimiento y desarrollo del tomate, especialmente en climas cálidos. Este virus invade los tejidos foliares y altera los procesos fisiológicos de la planta, generando deformaciones y reducciones importantes en el rendimiento. Es una enfermedad de rápida propagación cuando el vector no está correctamente controlado.',
     'Los síntomas característicos incluyen hojas rizadas hacia arriba o hacia adentro, crecimiento atrofiado, brotes deformados y reducción del tamaño foliar. Las plantas infectadas presentan un porte compacto, entrenudos cortos y, en muchos casos, clorosis entre nervaduras. La producción de flores y frutos disminuye notablemente, y los frutos que llegan a formarse suelen ser pequeños y de mala calidad.',
     'Dado que no existe un tratamiento curativo contra este virus, la medida principal es eliminar de inmediato las plantas infectadas para evitar su propagación dentro del cultivo. El manejo debe centrarse en el control efectivo de la mosca blanca mediante trampas cromáticas, aceites hortícolas, extractos botánicos o control biológico. Una intervención temprana sobre el vector reduce significativamente la incidencia.',
     'La enfermedad aparece y se desarrolla principalmente cuando el vector no está controlado, en ambientes cálidos con alta presencia de mosca blanca y en cultivos con poco monitoreo. La existencia de malezas cercanas que sirven de hospedero adicional y el uso de plántulas infectadas también favorecen la aparición de brotes.',
     'Las medidas preventivas incluyen el control riguroso de insectos vectores, la eliminación de malezas y plantas voluntarias, el uso de plántulas certificadas libres de virus, la instalación de mallas anti-insectos en invernaderos y el monitoreo constante de la población de mosca blanca. Mantener el cultivo limpio y aplicar buenas prácticas agrícolas ayuda a disminuir significativamente el riesgo de infección.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999179/enf9_vd22gh.png', 'VERANO', 'MODERADA'),

    (10, 'Nematodo de la raíz del café', 'Pratylenchus coffeae', 'NEMATODO',
     'Nematodo fitoparasitario que ataca el sistema radicular del café y de numerosas plantas tropicales. Se alimenta del tejido interno de las raíces, provocando lesiones que reducen la capacidad de absorción de agua y nutrientes. Su actividad disminuye notablemente el vigor de la planta, generando pérdidas de crecimiento y afectando la productividad en plantaciones cafetaleras y cultivos asociados.',
     'Los síntomas incluyen raíces ennegrecidas, necrosadas o con lesiones alargadas de color marrón. Las plantas afectadas presentan menor desarrollo radicular, crecimiento lento y hojas que muestran clorosis o marchitez en horas de alta radiación. En casos avanzados, el árbol puede perder vigor general, producir menos frutos y volverse más susceptible a enfermedades secundarias.',
     'El manejo recomendado incluye la solarización del suelo, técnica que consiste en cubrirlo con plástico transparente para elevar su temperatura y reducir la población de nematodos. También puede complementarse con control biológico mediante microorganismos antagonistas que limitan el desarrollo de Pratylenchus. La eliminación de plantas severamente afectadas y la desinfección de herramientas son prácticas clave para evitar la dispersión.',
     'La principal causa de aparición está asociada a suelos previamente contaminados, el uso de material vegetal infectado y la falta de higiene en labores agrícolas. El movimiento de tierra, agua o herramientas contaminadas facilita la propagación del nematodo entre diferentes áreas del cultivo.',
     'Para prevenir su presencia se recomienda implementar rotación de cultivos con especies menos susceptibles, aplicar microorganismos benéficos que mejoren la salud del suelo, desinfectar herramientas antes de cada uso y utilizar plantas certificadas libres de patógenos. Un monitoreo constante de las raíces y un manejo adecuado del suelo ayudan a reducir la incidencia del nematodo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999179/enf10_taos3y.jpg', 'OTOÑO', 'LEVE');

-- 1️⃣ Tizón tardío del tomate
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (1, 'late blight'),
                                                        (1, 'tizón tardío'),
                                                        (1, 'tizon tardio'),
                                                        (1, 'tizón de la papa'),
                                                        (1, 'tizón del tomate'),
                                                        (1, 'phytophthora infestans'),
                                                        (1, 'blight tomato disease'),
                                                        (1, 'tomato late blight'),
                                                        (1, 'potato blight'),
                                                        (1, 'tizón fungoso'),
                                                        (1, 'enfermedad del tizón'),
                                                        (1, 'tizón húmedo'),
                                                        (1, 'tomato fungus blight'),
                                                        (1, 'hongos del tomate'),
                                                        (1, 'leaf blight');

-- 2️⃣ Roya del café
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (2, 'coffee rust'),
                                                        (2, 'roya del cafeto'),
                                                        (2, 'roya del café'),
                                                        (2, 'hemileia vastatrix'),
                                                        (2, 'leaf rust coffee'),
                                                        (2, 'coffee leaf rust'),
                                                        (2, 'coffee fungus'),
                                                        (2, 'roya anaranjada'),
                                                        (2, 'rust coffee leaves'),
                                                        (2, 'hongos del café'),
                                                        (2, 'rust fungus'),
                                                        (2, 'roya amarilla'),
                                                        (2, 'hemileia'),
                                                        (2, 'orange leaf rust'),
                                                        (2, 'coffee disease rust');

-- 3️⃣ Marchitez bacteriana del plátano
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (3, 'bacterial wilt'),
                                                        (3, 'moko disease'),
                                                        (3, 'marchitez bacteriana'),
                                                        (3, 'marchitez del plátano'),
                                                        (3, 'marchitez vascular del platano'),
                                                        (3, 'ralstonia solanacearum'),
                                                        (3, 'banana wilt'),
                                                        (3, 'banana moko'),
                                                        (3, 'bacterial banana wilt'),
                                                        (3, 'vascular wilt banana'),
                                                        (3, 'enfermedad del moko'),
                                                        (3, 'plátano enfermo'),
                                                        (3, 'wilt banana disease'),
                                                        (3, 'plátano marchito'),
                                                        (3, 'xanthomonas banana wilt');

-- 4️⃣ Virus del mosaico del pepino
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (4, 'cucumber mosaic'),
                                                        (4, 'mosaico del pepino'),
                                                        (4, 'virus del mosaico del pepino'),
                                                        (4, 'cmv virus'),
                                                        (4, 'virus del mosaico del pepinillo'),
                                                        (4, 'cucumber mosaic virus infection'),
                                                        (4, 'pepino con mosaico'),
                                                        (4, 'mosaico vegetal'),
                                                        (4, 'mosaico de hojas'),
                                                        (4, 'virus del mosaico vegetal'),
                                                        (4, 'virus cmv'),
                                                        (4, 'cucumber leaf mosaic'),
                                                        (4, 'mosaic leaf disease'),
                                                        (4, 'mosaico cucurbitáceas'),
                                                        (4, 'virus de hojas manchadas');

-- 5️⃣ Mancha negra de la rosa
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (5, 'black spot rose'),
                                                        (5, 'black spot disease'),
                                                        (5, 'mancha negra de la rosa'),
                                                        (5, 'mancha negra en rosales'),
                                                        (5, 'diplocarpon rosae'),
                                                        (5, 'blackspot fungus'),
                                                        (5, 'hongos de rosa'),
                                                        (5, 'leaf spot rose'),
                                                        (5, 'black spot fungus'),
                                                        (5, 'manchas en pétalos'),
                                                        (5, 'black spots on rose leaves'),
                                                        (5, 'rose leaf black spot'),
                                                        (5, 'rosa enferma'),
                                                        (5, 'black spot infection'),
                                                        (5, 'leaf spot disease');

-- 6️⃣ Pudrición de raíz
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (6, 'root rot'),
                                                        (6, 'fusarium wilt'),
                                                        (6, 'fusarium oxysporum'),
                                                        (6, 'pudrición radicular'),
                                                        (6, 'root decay'),
                                                        (6, 'root fungus'),
                                                        (6, 'rhizoctonia'),
                                                        (6, 'pudricion de la raíz'),
                                                        (6, 'root rot disease'),
                                                        (6, 'plant root rot'),
                                                        (6, 'hongo del suelo'),
                                                        (6, 'soil fungus rot'),
                                                        (6, 'root infection'),
                                                        (6, 'fungal rot'),
                                                        (6, 'pudrición del tallo');

-- 7️⃣ Agalla bacteriana
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (7, 'crown gall'),
                                                        (7, 'agalla bacteriana'),
                                                        (7, 'agalla de la raíz'),
                                                        (7, 'agrobacterium'),
                                                        (7, 'agrobacterium tumefaciens'),
                                                        (7, 'tumor bacteriano'),
                                                        (7, 'bacterial gall'),
                                                        (7, 'bacterial crown gall'),
                                                        (7, 'tumor vegetal'),
                                                        (7, 'root crown gall'),
                                                        (7, 'planta con agallas'),
                                                        (7, 'crown gall disease'),
                                                        (7, 'gall tumor plant'),
                                                        (7, 'bacterial plant tumor'),
                                                        (7, 'root gall infection');

-- 8️⃣ Mildiu polvoriento
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (8, 'powdery mildew'),
                                                        (8, 'mildiu polvoriento'),
                                                        (8, 'mildiu blanco'),
                                                        (8, 'oidio'),
                                                        (8, 'erysiphe'),
                                                        (8, 'mildiu en hojas'),
                                                        (8, 'leaf mildew'),
                                                        (8, 'white mildew'),
                                                        (8, 'powder mildew'),
                                                        (8, 'oidio de hojas'),
                                                        (8, 'mildiu superficial'),
                                                        (8, 'hongos blancos'),
                                                        (8, 'mildiu de tallo'),
                                                        (8, 'blanca en hojas'),
                                                        (8, 'mildew fungus');

-- 9️⃣ Virus del enrollamiento de la hoja del tomate
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (9, 'tomato leaf curl'),
                                                        (9, 'tlcv'),
                                                        (9, 'leaf curl virus'),
                                                        (9, 'virus del rizado de hojas de tomate'),
                                                        (9, 'tomato curl'),
                                                        (9, 'virus de enrollamiento'),
                                                        (9, 'tomato yellow leaf curl'),
                                                        (9, 'leaf curling virus'),
                                                        (9, 'rizado de hoja'),
                                                        (9, 'hojas arrugadas tomate'),
                                                        (9, 'virus de hojas amarillas'),
                                                        (9, 'tomato leaf twist'),
                                                        (9, 'virus del tomate amarillo'),
                                                        (9, 'leaf curl disease'),
                                                        (9, 'tomato leaf roll');

-- 🔟 Nematodo de la raíz del café
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (10, 'root lesion nematode'),
                                                        (10, 'pratylenchus'),
                                                        (10, 'pratylenchus coffeae'),
                                                        (10, 'nematodo del café'),
                                                        (10, 'root nematode coffee'),
                                                        (10, 'nemátodo parásito'),
                                                        (10, 'root lesion'),
                                                        (10, 'nematodo de raíces finas'),
                                                        (10, 'coffee nematode'),
                                                        (10, 'root-knot nematode coffee'),
                                                        (10, 'plaga de raíces del café'),
                                                        (10, 'root pest coffee'),
                                                        (10, 'nemátodo del cafeto'),
                                                        (10, 'root disease coffee'),
                                                        (10, 'nematode infection coffee');
-- ===========================================
-- 📰 TABLA: BLOG
-- ===========================================

INSERT INTO blog (blogid, usuarioid, blogtipo, blogtitulo, blogdescripcion, blogimagen, blogestado, blogfechapublicacion)
VALUES
    (1, 1, 'NEWS', 'EcoShield lanza su nueva IA para detección de plagas',
     'EcoShield presenta su nueva tecnología de inteligencia artificial especializada en el reconocimiento de plagas y enfermedades agrícolas. Este sistema alcanza una precisión promedio del 95% al analizar imágenes de hojas, frutos y tallos, permitiendo al agricultor actuar de manera temprana y reducir el uso innecesario de pesticidas químicos. La actualización marca un paso importante hacia una agricultura más eficiente y sostenible.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog1_ku9zsf.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (2, 1, 'TIP', '5 prácticas sostenibles para proteger tus cultivos',
     'La implementación de prácticas agrícolas sostenibles como el riego por goteo, la rotación de cultivos, la incorporación de materia orgánica, el uso de biopesticidas y el monitoreo constante del suelo permite mejorar la salud de las plantas y preservar la biodiversidad. En esta guía conocerás cómo aplicar estas estrategias para asegurar un cultivo más productivo y resistente a plagas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog2_eyalkf.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (3, 2, 'NEWS', 'Nuevo acuerdo con instituciones agrícolas latinoamericanas',
     'EcoShield ha establecido una alianza estratégica con instituciones agrícolas de Perú, Colombia y México para fortalecer la digitalización y el monitoreo inteligente de plagas en la región. Este convenio permitirá ampliar la base de datos de detecciones en campo, mejorar la precisión de los modelos predictivos y promover el uso de herramientas tecnológicas en cientos de comunidades agrícolas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997491/blog3_yequj6.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (4, 2, 'TIP', 'Cómo identificar señales tempranas de enfermedades en tus plantas',
     'Detectar de forma temprana manchas irregulares, cambios en el color del follaje, pérdida de brillo o deformaciones en los brotes puede evitar la propagación de enfermedades. En esta publicación aprenderás a reconocer los indicadores visuales clave que EcoShield analiza mediante IA para facilitar un diagnóstico oportuno y aumentar la probabilidad de recuperación del cultivo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997489/blog4_vojbxj.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (5, 1, 'TIP', 'Ahorra agua sin afectar la salud de tus cultivos',
     'El uso de sensores de humedad, sistemas de riego inteligente y técnicas de retención de agua en el suelo puede reducir hasta un 40% el consumo hídrico sin comprometer el crecimiento del cultivo. Conoce cómo configurar alertas personalizadas y aprovechar las herramientas de EcoShield para automatizar el riego y evitar excesos o deficiencias.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog5_a32d8y.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (6, 2, 'NEWS', 'EcoShield alcanza 10,000 usuarios activos en Latinoamérica',
     'Gracias al crecimiento constante de la comunidad agrícola y urbana, EcoShield ha superado los 10,000 usuarios activos en toda Latinoamérica. Este hito refleja el interés creciente por soluciones tecnológicas sostenibles que facilitan el monitoreo de cultivos y la toma de decisiones basadas en datos. El equipo continúa trabajando para ampliar las funcionalidades y llegar a más productores en la región.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997489/blog6_bfymep.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (7, 1, 'TIP', 'Guía rápida para interpretar tus resultados de detección',
     'Cuando EcoShield identifica una plaga o enfermedad, genera un informe con información detallada como el nivel de confianza del análisis, el tipo de daño detectado y recomendaciones de manejo. En esta guía aprenderás a interpretar cada sección del reporte para actuar de forma precisa y oportuna en tus cultivos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog7_cehkup.png',
     'ACTIVO', CURRENT_TIMESTAMP),

    (8, 1, 'NEWS', 'Implementamos nuevos filtros de búsqueda en el catálogo de plagas',
     'EcoShield incorpora nuevos filtros de búsqueda que permiten ordenar plagas y enfermedades según su tipo, severidad y temporada. Esta actualización mejora la accesibilidad del catálogo y facilita que los usuarios encuentren información relevante para los cultivos presentes en su zona.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog8_jz1ebd.png',
     'ACTIVO', CURRENT_TIMESTAMP),

    (9, 2, 'TIP', 'Cómo mantener tu huerto libre de plagas sin químicos',
     'Existen métodos naturales para mantener un huerto sano sin recurrir a pesticidas químicos. El uso de trampas cromáticas, plantas asociadas y biopesticidas orgánicos permite reducir la aparición de plagas de forma segura y sostenible. Aprende cómo aplicarlos para proteger tu huerto urbano.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog9_x9j7jz.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (10, 2, 'NEWS', 'EcoShield presenta su módulo de análisis climático en tiempo real',
     'EcoShield incorpora un módulo avanzado de análisis climático que ofrece alertas personalizadas basadas en condiciones ambientales. Estas notificaciones anticipan escenarios que favorecen la aparición de plagas o enfermedades, ayudando a los agricultores a prevenir daños y planificar sus actividades con mayor precisión.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog10_xoazwn.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (11, 1, 'TIP', 'Cómo almacenar correctamente tus productos biológicos',
     'Los productos biológicos como biopesticidas y biofertilizantes pueden perder efectividad cuando se exponen a luz solar directa, altas temperaturas o humedad. En esta guía encontrarás recomendaciones prácticas para almacenarlos correctamente y mantener su potencia durante más tiempo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997491/blog11_vsjumk.jpg',
     'INACTIVO', CURRENT_TIMESTAMP),

    (12, 2, 'NEWS', 'Actualización del ecosistema EcoShield 1.0',
     'La versión 1.0 del ecosistema EcoShield introduce mejoras significativas en la velocidad de análisis, la gestión de imágenes y el soporte para archivos de alta resolución. Estas optimizaciones permiten un procesamiento más preciso, una experiencia de usuario más fluida y un mejor aprovechamiento de los recursos de detección basados en IA.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997491/blog12_tje8ro.png',
     'ARCHIVADO', CURRENT_TIMESTAMP);

-- ========================
-- 📸 TABLA: POST
-- ========================
INSERT INTO post (postid, usuarioid, posttitulo, postdescripcion, postfoto, postfecha)
VALUES
    (1, 2, 'Problemas con hongos en hojas de tomate',
     'Mis plantas de tomate muestran manchas oscuras y secas en las hojas inferiores. Alguien sabe si esto es tizón o alguna otra enfermedad?',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998075/post1_xibc3f.jpg',
     '2025-09-28 14:32:10'),

    (2, 4, 'Plaga en hojas de lechuga — ayuda urgente',
     'Encontré pequeños insectos blancos en el envés de mis hojas de lechuga. ¿Podría tratarse de mosca blanca? ¿Qué puedo hacer?',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998076/post2_z37ybc.jpg',
     '2025-10-03 09:17:54'),

    (3, 6, 'Resultados con biopesticidas naturales',
     'He estado probando extracto de ajo y neem para controlar pulgones, y los resultados han sido buenos. Les comparto mi experiencia.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998075/post3_xh9rmz.jpg',
     '2025-10-11 19:45:22'),

    (4, 9, 'Recomendaciones para cultivar fresas en clima seco',
     'Vivo en una zona con poca humedad y me cuesta mantener las fresas saludables. ¿Qué consejos pueden darme para evitar plagas y deshidratación?',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998076/post4_xpjkdi.jpg',
     '2025-10-19 07:58:43'),

    (5, 10, 'Nuevo usuario de EcoShield — mi primera detección',
     'Probé el sistema de detección de plagas y me funcionó muy bien. Detectó una posible infestación de trips en mis pimientos. ¡Recomendado!',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998076/post5_lpoyd9.png',
     '2025-11-02 16:24:11');


-- ========================
-- 💬 TABLA: COMENTARIO
-- ========================
INSERT INTO comentario (comentarioid, postid, usuarioid, comentariotexto, comentariofecha)
VALUES
-- Comentarios del Post 1
(1, 1, 3, 'Sí, parece tizón tardío. Puedes confirmarlo subiendo una foto más cercana.', CURRENT_TIMESTAMP),
(2, 1, 8, 'Si es tizón, evita mojar las hojas al regar y usa Trichoderma.', CURRENT_TIMESTAMP),
(3, 1, 1, 'Buen aporte. También puedes revisar el nivel de humedad del sustrato.', CURRENT_TIMESTAMP),

-- Comentarios del Post 2
(4, 2, 5, 'Por la descripción, suena a mosca blanca. Usa trampas amarillas.', CURRENT_TIMESTAMP),
(5, 2, 12, 'Yo tuve el mismo problema, el jabón potásico ayudó mucho.', CURRENT_TIMESTAMP),
(6, 2, 1, 'Correcto, es mosca blanca. Controla la ventilación y elimina hojas afectadas.', CURRENT_TIMESTAMP),

-- Comentarios del Post 3
(7, 3, 2, 'Excelente, el neem es muy efectivo contra pulgones y cochinillas.', CURRENT_TIMESTAMP),
(8, 3, 11, 'Gracias por compartir, probaré esa mezcla con mis plantas ornamentales.', CURRENT_TIMESTAMP),
(9, 3, 8, 'Recuerda no aplicar al mediodía, puede quemar las hojas.', CURRENT_TIMESTAMP),

-- Comentarios del Post 4
(10, 4, 6, 'En climas secos puedes usar mulch para mantener la humedad.', CURRENT_TIMESTAMP),
(11, 4, 10, 'Riega temprano y usa cobertura vegetal. Me ha funcionado bien.', CURRENT_TIMESTAMP),

-- Comentarios del Post 5
(12, 5, 4, '¡Genial! Yo también tuve detecciones precisas en tomates.', CURRENT_TIMESTAMP),
(13, 5, 9, 'El modelo ha mejorado mucho en las últimas versiones.', CURRENT_TIMESTAMP),
(14, 5, 1, 'Gracias por compartir tu experiencia, seguimos mejorando el sistema.', CURRENT_TIMESTAMP);


-- ========================
-- 💬 TABLA: FEEDBACK
-- ========================
INSERT INTO feedback (feedbackid, usuarioid, feedbacktipo, feedbackdescripcion, feedbackrating, feedbackfecha)
VALUES
    (1, 8, 'GENERAL', 'La aplicación EcoShield me parece intuitiva y fácil de usar. Excelente interfaz.', 5, CURRENT_TIMESTAMP),
    (2, 3, 'SUGGESTION', 'Sería genial que la app permita comparar plagas detectadas entre usuarios.', 4, CURRENT_TIMESTAMP),
    (3, 4, 'APP_PROBLEM', 'En algunos casos la detección tarda demasiado cuando la conexión es lenta.', 3, CURRENT_TIMESTAMP),
    (4, 8, 'GENERAL', 'EcoShield ha mejorado mi producción agrícola reduciendo el uso de pesticidas.', 5, CURRENT_TIMESTAMP),
    (5, 6, 'SUGGESTION', 'Podrían incluir recordatorios de fumigación o tratamiento automático.', 4, CURRENT_TIMESTAMP),
    (6, 7, 'APP_PROBLEM', 'No puedo subir fotos grandes, sería bueno un mensaje de error más claro.', 2, CURRENT_TIMESTAMP),
    (7, 9, 'GENERAL', 'Muy útil para identificar enfermedades del tomate. Resultados precisos.', 5, CURRENT_TIMESTAMP),
    (8, 10, 'SUGGESTION', 'Faltan más variedades de plagas para cultivos de maíz.', 4, CURRENT_TIMESTAMP),
    (9, 11, 'GENERAL', 'Excelente iniciativa ecológica, promueve la agricultura sostenible.', 5, CURRENT_TIMESTAMP),
    (10, 12, 'APP_PROBLEM', 'En iOS se cierra la app al abrir el historial de detecciones.', 2, CURRENT_TIMESTAMP),
    (11, 5, 'SUGGESTION', 'Podrían incluir consejos según el clima de mi región.', 4, CURRENT_TIMESTAMP),
    (12, 12, 'GENERAL', 'Desde el panel de administrador puedo gestionar todo fácilmente. Muy bien.', 5, CURRENT_TIMESTAMP);


-- ========================
-- 💬 TABLA: POST_LIKE
-- ========================
INSERT INTO post_like (postid, usuarioid, fechalike)
VALUES
    (1, 3, CURRENT_TIMESTAMP),
    (1, 8, CURRENT_TIMESTAMP),
    (1, 1, CURRENT_TIMESTAMP),
    (2, 4, CURRENT_TIMESTAMP),
    (2, 5, CURRENT_TIMESTAMP),
    (3, 2, CURRENT_TIMESTAMP),
    (4, 6, CURRENT_TIMESTAMP),
    (5, 10, CURRENT_TIMESTAMP),
    (5, 1, CURRENT_TIMESTAMP),
    (5, 9, CURRENT_TIMESTAMP);



-- ========================
-- 💬 TABLA: COMENTARIO_LIKE
-- ========================
INSERT INTO comentario_like (comentarioid, usuarioid, fechalike)
VALUES
    (1, 1, CURRENT_TIMESTAMP),
    (1, 8, CURRENT_TIMESTAMP),
    (2, 3, CURRENT_TIMESTAMP),
    (3, 1, CURRENT_TIMESTAMP),
    (4, 5, CURRENT_TIMESTAMP),
    (5, 12, CURRENT_TIMESTAMP),
    (6, 1, CURRENT_TIMESTAMP),
    (7, 2, CURRENT_TIMESTAMP),
    (8, 11, CURRENT_TIMESTAMP),
    (9, 8, CURRENT_TIMESTAMP),
    (10, 6, CURRENT_TIMESTAMP),
    (11, 10, CURRENT_TIMESTAMP),
    (12, 4, CURRENT_TIMESTAMP),
    (13, 9, CURRENT_TIMESTAMP),
    (14, 1, CURRENT_TIMESTAMP);




SELECT setval(pg_get_serial_sequence('rol', 'rolid'), COALESCE(MAX(rolid), 0) + 1, false) FROM rol;
SELECT setval(pg_get_serial_sequence('usuario', 'usuarioid'), COALESCE(MAX(usuarioid), 0) + 1, false) FROM usuario;
SELECT setval(pg_get_serial_sequence('plaga', 'plagaid'), COALESCE(MAX(plagaid), 0) + 1, false) FROM plaga;
SELECT setval(pg_get_serial_sequence('enfermedad', 'enfermedadid'), COALESCE(MAX(enfermedadid), 0) + 1, false) FROM enfermedad;
SELECT setval(pg_get_serial_sequence('blog', 'blogid'), COALESCE(MAX(blogid), 0) + 1, false) FROM blog;
SELECT setval(pg_get_serial_sequence('post', 'postid'), COALESCE(MAX(postid), 0) + 1, false) FROM post;
SELECT setval(pg_get_serial_sequence('comentario', 'comentarioid'), COALESCE(MAX(comentarioid), 0) + 1, false) FROM comentario;
SELECT setval(pg_get_serial_sequence('feedback', 'feedbackid'), COALESCE(MAX(feedbackid), 0) + 1, false) FROM feedback;
SELECT setval(pg_get_serial_sequence('post_like', 'likeid'), COALESCE(MAX(likeid), 0) + 1, false) FROM post_like;
SELECT setval(pg_get_serial_sequence('comentario_like', 'likeid'), COALESCE(MAX(likeid), 0) + 1,false) FROM comentario_like;
