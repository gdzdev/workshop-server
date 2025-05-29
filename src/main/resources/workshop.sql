USE workshop;

-- Insertar categorias de prueba
INSERT INTO categories (name, description, image_url, created_at, updated_at) VALUES
('Repuestos de Motor', 'Repuestos y piezas para motores de motocicletas', 'https://example.com/motor-parts.jpg', NOW(), NOW()),
('Herramientas Manuales', 'Herramientas manuales para reparaciones de motos', 'https://example.com/hand-tools.jpg', NOW(), NOW()),
('Lubricantes y Fluidos', 'Aceites, lubricantes y fluidos para motocicletas', 'https://example.com/lubricants.jpg', NOW(), NOW()),
('Frenos y Embragues', 'Componentes para sistemas de frenos y embragues de motos', 'https://example.com/brakes-clutches.jpg', NOW(), NOW()),
('Suspensión y Dirección', 'Piezas para sistemas de suspensión y dirección de motos', 'https://example.com/suspension-steering.jpg', NOW(), NOW()),
('Accesorios y Personalización', 'Accesorios para personalizar y mejorar motocicletas', 'https://example.com/moto-accessories.jpg', NOW(), NOW());

-- Insertar productos de prueba
INSERT INTO products (code, name, image_url, stock, cost, price, available, category_id, created_at, updated_at) VALUES
-- Repuestos de Motor
('R001', 'Filtro de Aceite para Moto', 'https://example.com/oil-filter-moto.jpg', 50, 8.50, 15.99, TRUE, 1, NOW(), NOW()),
('R002', 'Bujía de Encendido para Moto', 'https://example.com/spark-plug-moto.jpg', 100, 4.75, 9.99, TRUE, 1, NOW(), NOW()),
('R003', 'Kit de Cadena y Piñones', 'https://example.com/chain-sprocket-kit.jpg', 30, 35.00, 65.50, TRUE, 1, NOW(), NOW()),
('R004', 'Carburador para Moto 150cc', 'https://example.com/carburetor-moto.jpg', 20, 45.00, 85.00, TRUE, 1, NOW(), NOW()),
('R005', 'Cilindro y Pistón 125cc', 'https://example.com/cylinder-piston-moto.jpg', 15, 60.00, 120.00, TRUE, 1, NOW(), NOW()),
('R006', 'Junta de Culata para Moto 150cc', 'https://example.com/head-gasket.jpg', 25, 12.50, 24.99, TRUE, 1, NOW(), NOW()),
('R007', 'Sensor de Temperatura para Moto', 'https://example.com/temp-sensor.jpg', 40, 8.75, 17.50, TRUE, 1, NOW(), NOW()),
('R008', 'Bomba de Aceite para Moto', 'https://example.com/oil-pump.jpg', 15, 28.00, 55.00, TRUE, 1, NOW(), NOW()),
('R009', 'Válvula de Admisión 125cc', 'https://example.com/intake-valve.jpg', 30, 9.50, 19.00, TRUE, 1, NOW(), NOW()),
('R010', 'Válvula de Escape 125cc', 'https://example.com/exhaust-valve.jpg', 30, 10.50, 21.00, TRUE, 1, NOW(), NOW()),
('R011', 'Retén de Cigüeñal', 'https://example.com/crankshaft-seal.jpg', 50, 5.25, 10.50, TRUE, 1, NOW(), NOW()),
('R012', 'Tensor de Cadena Automático', 'https://example.com/chain-tensioner.jpg', 20, 18.00, 36.00, TRUE, 1, NOW(), NOW()),
('R013', 'Kit de Juntas Completo', 'https://example.com/gasket-set.jpg', 10, 35.00, 70.00, TRUE, 1, NOW(), NOW()),
('R014', 'Termostato para Moto', 'https://example.com/thermostat.jpg', 25, 12.00, 24.00, TRUE, 1, NOW(), NOW()),
('R015', 'Radiador para Moto 250cc', 'https://example.com/radiator.jpg', 12, 45.00, 90.00, TRUE, 1, NOW(), NOW()),
('R016', 'Tapa de Depósito de Aceite', 'https://example.com/oil-cap.jpg', 40, 4.50, 9.00, TRUE, 1, NOW(), NOW()),
('R017', 'Filtro de Aire Deportivo', 'https://example.com/performance-air-filter.jpg', 35, 15.00, 30.00, TRUE, 1, NOW(), NOW()),
('R018', 'Correa de Distribución', 'https://example.com/timing-belt.jpg', 18, 22.00, 44.00, TRUE, 1, NOW(), NOW()),
('R019', 'Bomba de Combustible Eléctrica', 'https://example.com/fuel-pump.jpg', 10, 38.00, 76.00, TRUE, 1, NOW(), NOW()),
('R020', 'Sensor de Posición del Acelerador', 'https://example.com/tps-sensor.jpg', 15, 25.00, 50.00, TRUE, 1, NOW(), NOW()),
('R021', 'Bobina de Encendido', 'https://example.com/ignition-coil.jpg', 30, 17.50, 35.00, TRUE, 1, NOW(), NOW()),
('R022', 'Cable de Bujía de Alto Rendimiento', 'https://example.com/spark-wire.jpg', 25, 9.00, 18.00, TRUE, 1, NOW(), NOW()),
('R023', 'Terminal de Batería', 'https://example.com/battery-terminal.jpg', 50, 3.50, 7.00, TRUE, 1, NOW(), NOW()),
('R024', 'Alternador para Moto', 'https://example.com/alternator.jpg', 8, 65.00, 130.00, TRUE, 1, NOW(), NOW()),
('R025', 'Regulador de Voltaje', 'https://example.com/voltage-regulator.jpg', 20, 22.00, 44.00, TRUE, 1, NOW(), NOW()),
-- Herramientas Manuales
('H001', 'Juego de Llaves para Moto', 'https://example.com/wrench-set-moto.jpg', 30, 22.50, 45.00, TRUE, 2, NOW(), NOW()),
('H002', 'Alicates de Corte para Cable', 'https://example.com/cable-cutters-moto.jpg', 40, 7.25, 14.50, TRUE, 2, NOW(), NOW()),
('H003', 'Destornillador de Estrella para Moto', 'https://example.com/star-screwdriver-moto.jpg', 50, 4.50, 9.00, TRUE, 2, NOW(), NOW()),
('H004', 'Kit de Reparación de Neumáticos', 'https://example.com/tire-repair-kit.jpg', 25, 18.00, 35.00, TRUE, 2, NOW(), NOW()),
('H005', 'Soporte para Moto', 'https://example.com/moto-stand.jpg', 20, 30.00, 60.00, TRUE, 2, NOW(), NOW()),

-- Lubricantes y Fluidos
('L001', 'Aceite de Motor 10W-40 para Moto', 'https://example.com/engine-oil-moto.jpg', 80, 12.75, 25.50, TRUE, 3, NOW(), NOW()),
('L002', 'Líquido de Frenos DOT 4', 'https://example.com/brake-fluid-moto.jpg', 60, 6.50, 12.99, TRUE, 3, NOW(), NOW()),
('L003', 'Aceite de Transmisión para Moto', 'https://example.com/transmission-oil-moto.jpg', 40, 18.00, 35.00, TRUE, 3, NOW(), NOW()),
('L004', 'Anticongelante para Moto', 'https://example.com/antifreeze-moto.jpg', 70, 9.25, 18.50, TRUE, 3, NOW(), NOW()),
('L005', 'Lubricante para Cadena', 'https://example.com/chain-lubricant.jpg', 90, 5.00, 10.00, TRUE, 3, NOW(), NOW()),
('H006', 'Extractor de Rodamientos', 'https://example.com/bearing-puller.jpg', 15, 28.00, 56.00, TRUE, 2, NOW(), NOW()),
('H007', 'Llave Torx T25', 'https://example.com/torx-t25.jpg', 40, 5.50, 11.00, TRUE, 2, NOW(), NOW()),
('H008', 'Juego de Destornilladores de Precisión', 'https://example.com/precision-screwdrivers.jpg', 25, 12.00, 24.00, TRUE, 2, NOW(), NOW()),
('H009', 'Llave de Bujías Profunda', 'https://example.com/deep-spark-wrench.jpg', 30, 8.00, 16.00, TRUE, 2, NOW(), NOW()),
('H010', 'Torquímetro Digital', 'https://example.com/digital-torque-wrench.jpg', 10, 85.00, 170.00, TRUE, 2, NOW(), NOW()),
('H011', 'Cortador de Cable Hidráulico', 'https://example.com/hydraulic-cable-cutter.jpg', 5, 120.00, 240.00, TRUE, 2, NOW(), NOW()),
('H012', 'Punzón Centrador', 'https://example.com/alignment-punch.jpg', 20, 6.50, 13.00, TRUE, 2, NOW(), NOW()),
('H013', 'Juego de Llaves Allen', 'https://example.com/allen-wrench-set.jpg', 35, 10.00, 20.00, TRUE, 2, NOW(), NOW()),
('H014', 'Martillo de Goma', 'https://example.com/rubber-mallet.jpg', 25, 9.00, 18.00, TRUE, 2, NOW(), NOW()),
('H015', 'Soporte para Motor', 'https://example.com/engine-stand.jpg', 8, 45.00, 90.00, TRUE, 2, NOW(), NOW()),
('H016', 'Pistola de Calor Industrial', 'https://example.com/heat-gun.jpg', 12, 35.00, 70.00, TRUE, 2, NOW(), NOW()),
('H017', 'Juego de Brocas para Metal', 'https://example.com/metal-drill-bits.jpg', 30, 15.00, 30.00, TRUE, 2, NOW(), NOW()),
('H018', 'Escariador para Guías de Válvula', 'https://example.com/valve-guide-reamer.jpg', 10, 42.00, 84.00, TRUE, 2, NOW(), NOW()),
('H019', 'Micrómetro Digital', 'https://example.com/digital-micrometer.jpg', 15, 55.00, 110.00, TRUE, 2, NOW(), NOW()),
('H020', 'Calibrador de Espesores', 'https://example.com/feeler-gauge.jpg', 40, 7.00, 14.00, TRUE, 2, NOW(), NOW()),
('H021', 'Extractor de Poleas', 'https://example.com/pulley-puller.jpg', 12, 32.00, 64.00, TRUE, 2, NOW(), NOW()),
('H022', 'Llave de Cadena', 'https://example.com/chain-wrench.jpg', 20, 18.00, 36.00, TRUE, 2, NOW(), NOW()),
('H023', 'Juego de Raspadores', 'https://example.com/scraper-set.jpg', 25, 11.00, 22.00, TRUE, 2, NOW(), NOW()),
('H024', 'Cepillo de Alambre Industrial', 'https://example.com/wire-brush.jpg', 50, 4.50, 9.00, TRUE, 2, NOW(), NOW()),
('H025', 'Prensa Hidráulica Portátil', 'https://example.com/hydraulic-press.jpg', 3, 150.00, 300.00, TRUE, 2, NOW(), NOW()),

-- Frenos y Embragues
('F001', 'Pastillas de Freno Delantero', 'https://example.com/brake-pads-moto.jpg', 60, 15.00, 30.00, TRUE, 4, NOW(), NOW()),
('F002', 'Disco de Freno Trasero', 'https://example.com/rear-brake-disc.jpg', 30, 40.00, 75.00, TRUE, 4, NOW(), NOW()),
('F003', 'Kit de Embrague para Moto', 'https://example.com/clutch-kit-moto.jpg', 25, 55.00, 110.00, TRUE, 4, NOW(), NOW()),
('F004', 'Cable de Embrague', 'https://example.com/clutch-cable.jpg', 35, 8.00, 16.00, TRUE, 4, NOW(), NOW()),
('F005', 'Bomba de Freno Trasero', 'https://example.com/rear-brake-pump.jpg', 10, 50.00, 95.00, TRUE, 4, NOW(), NOW()),
('F006', 'Pastillas de Freno Orgánicas', 'https://example.com/organic-brake-pads.jpg', 40, 16.00, 32.00, TRUE, 4, NOW(), NOW()),
('F007', 'Pastillas de Freno Sinterizadas', 'https://example.com/sintered-brake-pads.jpg', 30, 20.00, 40.00, TRUE, 4, NOW(), NOW()),
('F008', 'Líquido de Frenos Racing DOT 4', 'https://example.com/racing-brake-fluid.jpg', 25, 12.00, 24.00, TRUE, 4, NOW(), NOW()),
('F009', 'Kit de Reconstrucción de Bomba de Freno', 'https://example.com/brake-pump-rebuild.jpg', 15, 18.00, 36.00, TRUE, 4, NOW(), NOW()),
('F010', 'Sensor de Desgaste de Pastillas', 'https://example.com/brake-wear-sensor.jpg', 50, 8.00, 16.00, TRUE, 4, NOW(), NOW()),
('F011', 'Manguera de Freno Trenzada', 'https://example.com/braided-brake-line.jpg', 20, 22.00, 44.00, TRUE, 4, NOW(), NOW()),
('F012', 'Plato de Embrague', 'https://example.com/clutch-plate.jpg', 25, 28.00, 56.00, TRUE, 4, NOW(), NOW()),
('F013', 'Resorte de Embrague', 'https://example.com/clutch-spring.jpg', 40, 6.50, 13.00, TRUE, 4, NOW(), NOW()),
('F014', 'Rodamiento de Empuje de Embrague', 'https://example.com/clutch-bearing.jpg', 30, 14.00, 28.00, TRUE, 4, NOW(), NOW()),
('F015', 'Cable de Freno Trasero', 'https://example.com/rear-brake-cable.jpg', 35, 9.00, 18.00, TRUE, 4, NOW(), NOW()),
('F016', 'Disco de Freno Wave', 'https://example.com/wave-brake-disc.jpg', 15, 65.00, 130.00, TRUE, 4, NOW(), NOW()),
('F017', 'Kit de Juntas para Bomba de Freno', 'https://example.com/brake-pump-gasket.jpg', 25, 11.00, 22.00, TRUE, 4, NOW(), NOW()),
('F018', 'Sensor ABS para Moto', 'https://example.com/abs-sensor.jpg', 10, 45.00, 90.00, TRUE, 4, NOW(), NOW()),
('F019', 'Guía de Pastillas de Freno', 'https://example.com/brake-pad-guide.jpg', 50, 5.00, 10.00, TRUE, 4, NOW(), NOW()),
('F020', 'Tornillos de Freno Avellanados', 'https://example.com/brake-screws.jpg', 100, 2.50, 5.00, TRUE, 4, NOW(), NOW()),

-- Suspensión y Dirección
('S001', 'Amortiguador Trasero', 'https://example.com/rear-shock-absorber.jpg', 20, 65.00, 130.00, TRUE, 5, NOW(), NOW()),
('S002', 'Kit de Rodamientos de Dirección', 'https://example.com/steering-bearings.jpg', 15, 25.00, 50.00, TRUE, 5, NOW(), NOW()),
('S003', 'Horquilla Delantera', 'https://example.com/front-fork.jpg', 10, 90.00, 180.00, TRUE, 5, NOW(), NOW()),
('S004', 'Barra de Dirección', 'https://example.com/handlebar.jpg', 30, 20.00, 40.00, TRUE, 5, NOW(), NOW()),
('S005', 'Kit de Retenes de Suspensión', 'https://example.com/suspension-seals.jpg', 25, 12.00, 24.00, TRUE, 5, NOW(), NOW()),
('S006', 'Kit de Retenes para Horquilla', 'https://example.com/fork-seal-kit.jpg', 25, 18.00, 36.00, TRUE, 5, NOW(), NOW()),
('S007', 'Aceite para Horquilla 10W', 'https://example.com/fork-oil.jpg', 30, 12.00, 24.00, TRUE, 5, NOW(), NOW()),
('S008', 'Barras Estabilizadoras', 'https://example.com/stabilizer-bars.jpg', 15, 55.00, 110.00, TRUE, 5, NOW(), NOW()),
('S009', 'Rodamiento de Rueda Delantera', 'https://example.com/front-wheel-bearing.jpg', 40, 14.00, 28.00, TRUE, 5, NOW(), NOW()),
('S010', 'Rodamiento de Rueda Trasera', 'https://example.com/rear-wheel-bearing.jpg', 40, 16.00, 32.00, TRUE, 5, NOW(), NOW()),
('S011', 'Kit de Reconstrucción de Amortiguador', 'https://example.com/shock-rebuild-kit.jpg', 20, 32.00, 64.00, TRUE, 5, NOW(), NOW()),
('S012', 'Resorte de Suspensión Trasera', 'https://example.com/rear-spring.jpg', 15, 45.00, 90.00, TRUE, 5, NOW(), NOW()),
('S013', 'Junta Tórica para Suspensión', 'https://example.com/suspension-o-ring.jpg', 60, 3.00, 6.00, TRUE, 5, NOW(), NOW()),
('S014', 'Soporte Inferior de Horquilla', 'https://example.com/fork-lower.jpg', 10, 75.00, 150.00, TRUE, 5, NOW(), NOW()),
('S015', 'Kit de Rodamientos de Dirección', 'https://example.com/steering-bearing-kit.jpg', 25, 22.00, 44.00, TRUE, 5, NOW(), NOW()),
('S016', 'Protector de Horquilla', 'https://example.com/fork-protector.jpg', 30, 15.00, 30.00, TRUE, 5, NOW(), NOW()),
('S017', 'Perno de Dirección', 'https://example.com/steering-stem.jpg', 12, 28.00, 56.00, TRUE, 5, NOW(), NOW()),
('S018', 'Junta de Basculante', 'https://example.com/swingarm-gasket.jpg', 25, 8.00, 16.00, TRUE, 5, NOW(), NOW()),
('S019', 'Kit de Cojinetes de Basculante', 'https://example.com/swingarm-bearings.jpg', 20, 18.00, 36.00, TRUE, 5, NOW(), NOW()),
('S020', 'Tensor de Cadena Ajustable', 'https://example.com/adjustable-chain-tensioner.jpg', 15, 25.00, 50.00, TRUE, 5, NOW(), NOW()),

-- Accesorios y Personalización
('A001', 'Espejos Retrovisores Deportivos', 'https://example.com/sport-mirrors.jpg', 40, 22.00, 44.00, TRUE, 6, NOW(), NOW()),
('A002', 'Manillar Cromado', 'https://example.com/chrome-handlebar.jpg', 20, 35.00, 70.00, TRUE, 6, NOW(), NOW()),
('A003', 'Sillín Deportivo', 'https://example.com/sport-seat.jpg', 15, 50.00, 100.00, TRUE, 6, NOW(), NOW()),
('A004', 'Luces LED para Moto', 'https://example.com/led-lights-moto.jpg', 50, 18.00, 36.00, TRUE, 6, NOW(), NOW()),
('A005', 'Portamatrículas Personalizado', 'https://example.com/custom-license-plate.jpg', 30, 10.00, 20.00, TRUE, 6, NOW(), NOW()),
('A006', 'Pegatina Kit Personalizado', 'https://example.com/custom-sticker-kit.jpg', 50, 15.00, 30.00, TRUE, 6, NOW(), NOW()),
('A007', 'Protector de Manillar', 'https://example.com/handlebar-protector.jpg', 30, 12.00, 24.00, TRUE, 6, NOW(), NOW()),
('A008', 'Porta Teléfono para Moto', 'https://example.com/phone-mount.jpg', 40, 18.00, 36.00, TRUE, 6, NOW(), NOW()),
('A009', 'Cubre Cadena Decorativo', 'https://example.com/chain-cover.jpg', 25, 22.00, 44.00, TRUE, 6, NOW(), NOW()),
('A010', 'Espejos Retrovisores Azules', 'https://example.com/blue-mirrors.jpg', 20, 28.00, 56.00, TRUE, 6, NOW(), NOW()),
('A011', 'Pedaleras Deportivas', 'https://example.com/sport-footpegs.jpg', 30, 35.00, 70.00, TRUE, 6, NOW(), NOW()),
('A012', 'Tapa de Depósito de Combustible', 'https://example.com/fuel-cap.jpg', 40, 16.00, 32.00, TRUE, 6, NOW(), NOW()),
('A013', 'Sistema de Iluminación LED', 'https://example.com/full-led-kit.jpg', 15, 65.00, 130.00, TRUE, 6, NOW(), NOW()),
('A014', 'Portamatrícula LED', 'https://example.com/led-license-plate.jpg', 25, 22.00, 44.00, TRUE, 6, NOW(), NOW()),
('A015', 'Cubre Escape', 'https://example.com/exhaust-cover.jpg', 20, 18.00, 36.00, TRUE, 6, NOW(), NOW()),
('A016', 'Asiento con Calefacción', 'https://example.com/heated-seat.jpg', 10, 85.00, 170.00, TRUE, 6, NOW(), NOW()),
('A017', 'Kit de Limpieza Premium', 'https://example.com/premium-cleaner-kit.jpg', 35, 25.00, 50.00, TRUE, 6, NOW(), NOW()),
('A018', 'Cubre Llantas Reflectante', 'https://example.com/rim-tape.jpg', 50, 8.00, 16.00, TRUE, 6, NOW(), NOW()),
('A019', 'Soporte para GPS', 'https://example.com/gps-mount.jpg', 25, 28.00, 56.00, TRUE, 6, NOW(), NOW()),
('A020', 'Alarma para Moto con Inmovilizador', 'https://example.com/moto-alarm.jpg', 15, 55.00, 110.00, TRUE, 6, NOW(), NOW()),
-- Lucricantes
('L006', 'Aceite Sintético 5W-30', 'https://example.com/synthetic-oil.jpg', 60, 14.00, 28.00, TRUE, 3, NOW(), NOW()),
('L007', 'Lubricante para Cable de Acelerador', 'https://example.com/throttle-cable-lube.jpg', 45, 6.00, 12.00, TRUE, 3, NOW(), NOW()),
('L008', 'Fluido de Frenos DOT 5.1', 'https://example.com/brake-fluid-dot5.jpg', 30, 8.50, 17.00, TRUE, 3, NOW(), NOW()),
('L009', 'Aceite para Amortiguadores', 'https://example.com/shock-oil.jpg', 25, 16.00, 32.00, TRUE, 3, NOW(), NOW()),
('L010', 'Lubricante Seco para Cerraduras', 'https://example.com/dry-lock-lube.jpg', 50, 5.50, 11.00, TRUE, 3, NOW(), NOW()),
('L011', 'Aceite para Transmisión 75W-90', 'https://example.com/gear-oil.jpg', 40, 12.00, 24.00, TRUE, 3, NOW(), NOW()),
('L012', 'Protector de Gomas y Juntas', 'https://example.com/rubber-protector.jpg', 35, 9.00, 18.00, TRUE, 3, NOW(), NOW()),
('L013', 'Limpiador de Frenos en Aerosol', 'https://example.com/brake-cleaner.jpg', 60, 7.50, 15.00, TRUE, 3, NOW(), NOW()),
('L014', 'Aceite para Cadenas con Cera', 'https://example.com/wax-chain-lube.jpg', 55, 8.00, 16.00, TRUE, 3, NOW(), NOW()),
('L015', 'Lubricante para Rodamientos', 'https://example.com/bearing-grease.jpg', 30, 10.00, 20.00, TRUE, 3, NOW(), NOW()),
('L016', 'Aditivo para Combustible', 'https://example.com/fuel-additive.jpg', 70, 6.50, 13.00, TRUE, 3, NOW(), NOW()),
('L017', 'Aceite para Motor 2T', 'https://example.com/2t-oil.jpg', 40, 11.00, 22.00, TRUE, 3, NOW(), NOW()),
('L018', 'Líquido Refrigerante Concentrado', 'https://example.com/coolant-concentrate.jpg', 25, 15.00, 30.00, TRUE, 3, NOW(), NOW()),
('L019', 'Lubricante para Pistones', 'https://example.com/piston-lube.jpg', 20, 12.50, 25.00, TRUE, 3, NOW(), NOW()),
('L020', 'Aceite para Hidráulico de Suspensión', 'https://example.com/suspension-fluid.jpg', 15, 18.00, 36.00, TRUE, 3, NOW(), NOW());


USE workshop;

INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Coca-Cola", 15, 0.1, 450.75, "2025-05-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Nestlé", 5, 0.05, 120.00, "2025-04-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Unilever", 20, 0.15, 700.50, "2025-03-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("PepsiCo", 10, 0.2, 300.00, "2025-02-28");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Procter & Gamble", 8, 0.12, 250.25, "2025-01-05");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Microsoft", 2, 0.03, 1200.00, "2024-12-01");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Apple", 1, 0.01, 1500.00, "2024-11-11");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Samsung", 3, 0.07, 800.00, "2024-10-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Sony", 7, 0.18, 400.00, "2024-09-01");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("LG", 12, 0.25, 600.00, "2024-08-14");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Adidas", 6, 0.09, 350.00, "2024-07-22");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Nike", 9, 0.11, 550.00, "2024-06-05");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Dell", 4, 0.06, 900.00, "2023-05-18");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("HP", 11, 0.13, 750.00, "2023-04-03");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Lenovo", 18, 0.17, 1100.00, "2023-03-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Amazon", 25, 0.3, 1500.00, "2023-02-14");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Google", 1, 0.02, 2000.00, "2023-01-01");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Meta", 2, 0.04, 1800.00, "2022-12-31");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Netflix", 3, 0.08, 250.00, "2022-11-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Disney", 5, 0.1, 400.00, "2022-10-07");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Dell", 15, 0.10, 850.00, "2025-05-23");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("HP", 10, 0.05, 600.00, "2025-05-22");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Lenovo", 20, 0.12, 1100.00, "2025-05-21");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Asus", 8, 0.08, 720.00, "2025-05-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Acer", 12, 0.07, 550.00, "2025-05-19");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("MSI", 5, 0.15, 950.00, "2025-05-18");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Razer", 3, 0.20, 1200.00, "2025-05-17");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Logitech", 25, 0.03, 300.00, "2025-05-16");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Corsair", 7, 0.10, 450.00, "2025-05-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("HyperX", 18, 0.06, 280.00, "2025-05-14");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Seagate", 10, 0.09, 150.00, "2025-04-30");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Western Digital", 14, 0.04, 180.00, "2025-04-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Crucial", 6, 0.11, 100.00, "2025-04-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Kingston", 22, 0.02, 80.00, "2025-04-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Samsung", 9, 0.08, 250.00, "2025-04-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Intel", 4, 0.15, 700.00, "2025-03-28");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("AMD", 7, 0.12, 600.00, "2025-03-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("NVIDIA", 2, 0.25, 1500.00, "2025-03-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("TP-Link", 30, 0.05, 120.00, "2025-03-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Netgear", 11, 0.10, 200.00, "2025-03-05");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("D-Link", 16, 0.07, 150.00, "2025-02-28");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Cisco", 1, 0.30, 2000.00, "2025-02-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Ubiquiti", 5, 0.18, 500.00, "2025-02-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Canon", 8, 0.10, 300.00, "2025-02-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Epson", 13, 0.06, 250.00, "2025-02-05");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("HP Inc.", 17, 0.09, 400.00, "2025-01-31");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Brother", 6, 0.04, 180.00, "2025-01-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Xerox", 2, 0.22, 900.00, "2025-01-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Lexmark", 9, 0.13, 350.00, "2025-01-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Ricoh", 4, 0.17, 600.00, "2025-01-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Panasonic", 10, 0.08, 280.00, "2024-12-31");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Sony", 7, 0.11, 500.00, "2024-12-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("LG", 12, 0.06, 400.00, "2024-12-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Philips", 18, 0.03, 150.00, "2024-12-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Bose", 3, 0.19, 700.00, "2024-12-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("JBL", 20, 0.07, 250.00, "2024-12-05");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Harman Kardon", 5, 0.14, 600.00, "2024-11-30");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Sennheiser", 8, 0.16, 450.00, "2024-11-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Audio-Technica", 11, 0.09, 320.00, "2024-11-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Shure", 6, 0.12, 550.00, "2024-11-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Blue Microphones", 4, 0.20, 380.00, "2024-10-31");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Rode", 7, 0.13, 420.00, "2024-10-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Zoom", 9, 0.08, 200.00, "2024-10-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("GoPro", 1, 0.25, 300.00, "2024-10-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("DJI", 2, 0.30, 800.00, "2024-10-10");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Garmin", 5, 0.15, 350.00, "2024-09-30");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Fitbit", 10, 0.10, 180.00, "2024-09-25");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Polar", 8, 0.12, 220.00, "2024-09-20");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Xiaomi", 15, 0.05, 100.00, "2024-09-15");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("OnePlus", 6, 0.07, 600.00, "2024-09-10");

INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Apple", 5, 0.10, 1500.00, "2025-05-24");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Google", 2, 0.05, 1200.00, "2025-05-24");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Microsoft", 3, 0.08, 900.00, "2025-05-24");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Amazon", 10, 0.12, 750.00, "2025-05-24");
INSERT INTO purchases(provider, count, discount, total_price, created_at) values ("Meta", 1, 0.15, 2000.00, "2025-05-24");