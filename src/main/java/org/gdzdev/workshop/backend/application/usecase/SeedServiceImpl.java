package org.gdzdev.workshop.backend.application.usecase;

import lombok.AllArgsConstructor;
import org.gdzdev.workshop.backend.domain.exception.InternalServerErrorException;

import org.gdzdev.workshop.backend.infrastructure.adapter.entity.CategoryEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.ProductEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.entity.PurchasesEntity;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.CategoryJpaRepository;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.ProductJpaRepository;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.PurchaseJpaRepository;
import org.gdzdev.workshop.backend.infrastructure.adapter.repos.SeedRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeedServiceImpl implements SeedRepository {
    private final ProductJpaRepository _productRepository;
    private final CategoryJpaRepository _categoryRepository;
    private final PurchaseJpaRepository _purchaseRepository;

    private ProductEntity generateProduct(
            String code,
            String name,
            String imageUrl,
            Integer stock,
            BigDecimal cost,
            BigDecimal price,
            Boolean available,
            Long category_id
    ) {
        ProductEntity product = new ProductEntity();
        Optional<CategoryEntity> category = this._categoryRepository.findById(category_id);

        // the error occur over here
        if (category.isEmpty()) throw new InternalServerErrorException("Something occur wrong");

        product.setCode(code);
        product.setName(name);
        product.setImageUrl(imageUrl);
        product.setStock(stock);
        product.setCost(cost);
        product.setPrice(price);
        product.setAvailable(available);
        product.setCategory(category.get());
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(LocalDateTime.now());
        return product;
    }

    public CategoryEntity generateCategory(
            String name,
            String description,
            String imageUrl
    ) {
        CategoryEntity category = new CategoryEntity();

        category.setName(name);
        category.setDescription(description);
        category.setImageUrl(imageUrl);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return category;
    }

    public PurchasesEntity generatePurchase(
            String provider,
            BigDecimal discount,
            BigDecimal totalPrice
    ) {
        PurchasesEntity purchase = new PurchasesEntity();

        purchase.setProvider(provider);
        purchase.setDiscount(discount);
        purchase.setTotalPrice(totalPrice);
        purchase.setCreatedAt(LocalDateTime.now());
        purchase.setUpdatedAt(LocalDateTime.now());

        return purchase;
    }

    public List<ProductEntity> products() {
        List<ProductEntity> products = new ArrayList<>();

        // Repuestos de Motor
        products.add(this.generateProduct("R001", "Filtro de Aceite para Moto", "https://example.com/oil-filter-moto.jpg", 50, BigDecimal.valueOf(8.50), BigDecimal.valueOf(15.99), true, 1L));
        products.add(this.generateProduct("R002", "Bujía de Encendido para Moto", "https://example.com/spark-plug-moto.jpg", 100, BigDecimal.valueOf(4.75), BigDecimal.valueOf(9.99), true, 1L));
        products.add(this.generateProduct("R003", "Kit de Cadena y Piñones", "https://example.com/chain-sprocket-kit.jpg", 30, BigDecimal.valueOf(35.00), BigDecimal.valueOf(65.50), true, 1L));
        products.add(this.generateProduct("R004", "Carburador para Moto 150cc", "https://example.com/carburetor-moto.jpg", 20, BigDecimal.valueOf(45.00), BigDecimal.valueOf(85.00), true, 1L));
        products.add(this.generateProduct("R005", "Cilindro y Pistón 125cc", "https://example.com/cylinder-piston-moto.jpg", 15, BigDecimal.valueOf(60.00), BigDecimal.valueOf(120.00), true, 1L));
        products.add(this.generateProduct("R006", "Junta de Culata para Moto 150cc", "https://example.com/head-gasket.jpg", 25, BigDecimal.valueOf(12.50), BigDecimal.valueOf(24.99), true, 1L));
        products.add(this.generateProduct("R007", "Sensor de Temperatura para Moto", "https://example.com/temp-sensor.jpg", 40, BigDecimal.valueOf(8.75), BigDecimal.valueOf(17.50), true, 1L));
        products.add(this.generateProduct("R008", "Bomba de Aceite para Moto", "https://example.com/oil-pump.jpg", 15, BigDecimal.valueOf(28.00), BigDecimal.valueOf(55.00), true, 1L));
        products.add(this.generateProduct("R009", "Válvula de Admisión 125cc", "https://example.com/intake-valve.jpg", 30, BigDecimal.valueOf(9.50), BigDecimal.valueOf(19.00), true, 1L));
        products.add(this.generateProduct("R010", "Válvula de Escape 125cc", "https://example.com/exhaust-valve.jpg", 30, BigDecimal.valueOf(10.50), BigDecimal.valueOf(21.00), true, 1L));
        products.add(this.generateProduct("R011", "Retén de Cigüeñal", "https://example.com/crankshaft-seal.jpg", 50, BigDecimal.valueOf(5.25), BigDecimal.valueOf(10.50), true, 1L));
        products.add(this.generateProduct("R012", "Tensor de Cadena Automático", "https://example.com/chain-tensioner.jpg", 20, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 1L));
        products.add(this.generateProduct("R013", "Kit de Juntas Completo", "https://example.com/gasket-set.jpg", 10, BigDecimal.valueOf(35.00), BigDecimal.valueOf(70.00), true, 1L));
        products.add(this.generateProduct("R014", "Termostato para Moto", "https://example.com/thermostat.jpg", 25, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 1L));
        products.add(this.generateProduct("R015", "Radiador para Moto 250cc", "https://example.com/radiator.jpg", 12, BigDecimal.valueOf(45.00), BigDecimal.valueOf(90.00), true, 1L));
        products.add(this.generateProduct("R016", "Tapa de Depósito de Aceite", "https://example.com/oil-cap.jpg", 40, BigDecimal.valueOf(4.50), BigDecimal.valueOf(9.00), true, 1L));
        products.add(this.generateProduct("R017", "Filtro de Aire Deportivo", "https://example.com/performance-air-filter.jpg", 35, BigDecimal.valueOf(15.00), BigDecimal.valueOf(30.00), true, 1L));
        products.add(this.generateProduct("R018", "Correa de Distribución", "https://example.com/timing-belt.jpg", 18, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 1L));
        products.add(this.generateProduct("R019", "Bomba de Combustible Eléctrica", "https://example.com/fuel-pump.jpg", 10, BigDecimal.valueOf(38.00), BigDecimal.valueOf(76.00), true, 1L));
        products.add(this.generateProduct("R020", "Sensor de Posición del Acelerador", "https://example.com/tps-sensor.jpg", 15, BigDecimal.valueOf(25.00), BigDecimal.valueOf(50.00), true, 1L));
        products.add(this.generateProduct("R021", "Bobina de Encendido", "https://example.com/ignition-coil.jpg", 30, BigDecimal.valueOf(17.50), BigDecimal.valueOf(35.00), true, 1L));
        products.add(this.generateProduct("R022", "Cable de Bujía de Alto Rendimiento", "https://example.com/spark-wire.jpg", 25, BigDecimal.valueOf(9.00), BigDecimal.valueOf(18.00), true, 1L));
        products.add(this.generateProduct("R023", "Terminal de Batería", "https://example.com/battery-terminal.jpg", 50, BigDecimal.valueOf(3.50), BigDecimal.valueOf(7.00), true, 1L));
        products.add(this.generateProduct("R024", "Alternador para Moto", "https://example.com/alternator.jpg", 8, BigDecimal.valueOf(65.00), BigDecimal.valueOf(130.00), true, 1L));
        products.add(this.generateProduct("R025", "Regulador de Voltaje", "https://example.com/voltage-regulator.jpg", 20, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 1L));

        // Herramientas Manuales
        products.add(this.generateProduct("H001", "Juego de Llaves para Moto", "https://example.com/wrench-set-moto.jpg", 30, BigDecimal.valueOf(22.50), BigDecimal.valueOf(45.00), true, 2L));
        products.add(this.generateProduct("H002", "Alicates de Corte para Cable", "https://example.com/cable-cutters-moto.jpg", 40, BigDecimal.valueOf(7.25), BigDecimal.valueOf(14.50), true, 2L));
        products.add(this.generateProduct("H003", "Destornillador de Estrella para Moto", "https://example.com/star-screwdriver-moto.jpg", 50, BigDecimal.valueOf(4.50), BigDecimal.valueOf(9.00), true, 2L));
        products.add(this.generateProduct("H004", "Kit de Reparación de Neumáticos", "https://example.com/tire-repair-kit.jpg", 25, BigDecimal.valueOf(18.00), BigDecimal.valueOf(35.00), true, 2L));
        products.add(this.generateProduct("H005", "Soporte para Moto", "https://example.com/moto-stand.jpg", 20, BigDecimal.valueOf(30.00), BigDecimal.valueOf(60.00), true, 2L));
        products.add(this.generateProduct("H006", "Extractor de Rodamientos", "https://example.com/bearing-puller.jpg", 15, BigDecimal.valueOf(28.00), BigDecimal.valueOf(56.00), true, 2L));
        products.add(this.generateProduct("H007", "Llave Torx T25", "https://example.com/torx-t25.jpg", 40, BigDecimal.valueOf(5.50), BigDecimal.valueOf(11.00), true, 2L));
        products.add(this.generateProduct("H008", "Juego de Destornilladores de Precisión", "https://example.com/precision-screwdrivers.jpg", 25, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 2L));
        products.add(this.generateProduct("H009", "Llave de Bujías Profunda", "https://example.com/deep-spark-wrench.jpg", 30, BigDecimal.valueOf(8.00), BigDecimal.valueOf(16.00), true, 2L));
        products.add(this.generateProduct("H010", "Torquímetro Digital", "https://example.com/digital-torque-wrench.jpg", 10, BigDecimal.valueOf(85.00), BigDecimal.valueOf(170.00), true, 2L));
        products.add(this.generateProduct("H011", "Cortador de Cable Hidráulico", "https://example.com/hydraulic-cable-cutter.jpg", 5, BigDecimal.valueOf(120.00), BigDecimal.valueOf(240.00), true, 2L));
        products.add(this.generateProduct("H012", "Punzón Centrador", "https://example.com/alignment-punch.jpg", 20, BigDecimal.valueOf(6.50), BigDecimal.valueOf(13.00), true, 2L));
        products.add(this.generateProduct("H013", "Juego de Llaves Allen", "https://example.com/allen-wrench-set.jpg", 35, BigDecimal.valueOf(10.00), BigDecimal.valueOf(20.00), true, 2L));
        products.add(this.generateProduct("H014", "Martillo de Goma", "https://example.com/rubber-mallet.jpg", 25, BigDecimal.valueOf(9.00), BigDecimal.valueOf(18.00), true, 2L));
        products.add(this.generateProduct("H015", "Soporte para Motor", "https://example.com/engine-stand.jpg", 8, BigDecimal.valueOf(45.00), BigDecimal.valueOf(90.00), true, 2L));
        products.add(this.generateProduct("H016", "Pistola de Calor Industrial", "https://example.com/heat-gun.jpg", 12, BigDecimal.valueOf(35.00), BigDecimal.valueOf(70.00), true, 2L));
        products.add(this.generateProduct("H017", "Juego de Brocas para Metal", "https://example.com/metal-drill-bits.jpg", 30, BigDecimal.valueOf(15.00), BigDecimal.valueOf(30.00), true, 2L));
        products.add(this.generateProduct("H018", "Escariador para Guías de Válvula", "https://example.com/valve-guide-reamer.jpg", 10, BigDecimal.valueOf(42.00), BigDecimal.valueOf(84.00), true, 2L));
        products.add(this.generateProduct("H019", "Micrómetro Digital", "https://example.com/digital-micrometer.jpg", 15, BigDecimal.valueOf(55.00), BigDecimal.valueOf(110.00), true, 2L));
        products.add(this.generateProduct("H020", "Calibrador de Espesores", "https://example.com/feeler-gauge.jpg", 40, BigDecimal.valueOf(7.00), BigDecimal.valueOf(14.00), true, 2L));
        products.add(this.generateProduct("H021", "Extractor de Poleas", "https://example.com/pulley-puller.jpg", 12, BigDecimal.valueOf(32.00), BigDecimal.valueOf(64.00), true, 2L));
        products.add(this.generateProduct("H022", "Llave de Cadena", "https://example.com/chain-wrench.jpg", 20, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 2L));
        products.add(this.generateProduct("H023", "Juego de Raspadores", "https://example.com/scraper-set.jpg", 25, BigDecimal.valueOf(11.00), BigDecimal.valueOf(22.00), true, 2L));
        products.add(this.generateProduct("H024", "Cepillo de Alambre Industrial", "https://example.com/wire-brush.jpg", 50, BigDecimal.valueOf(4.50), BigDecimal.valueOf(9.00), true, 2L));
        products.add(this.generateProduct("H025", "Prensa Hidráulica Portátil", "https://example.com/hydraulic-press.jpg", 3, BigDecimal.valueOf(150.00), BigDecimal.valueOf(300.00), true, 2L));

        // Lubricantes y Fluidos
        products.add(this.generateProduct("L001", "Aceite de Motor 10W-40 para Moto", "https://example.com/engine-oil-moto.jpg", 80, BigDecimal.valueOf(12.75), BigDecimal.valueOf(25.50), true, 3L));
        products.add(this.generateProduct("L002", "Líquido de Frenos DOT 4", "https://example.com/brake-fluid-moto.jpg", 60, BigDecimal.valueOf(6.50), BigDecimal.valueOf(12.99), true, 3L));
        products.add(this.generateProduct("L003", "Aceite de Transmisión para Moto", "https://example.com/transmission-oil-moto.jpg", 40, BigDecimal.valueOf(18.00), BigDecimal.valueOf(35.00), true, 3L));
        products.add(this.generateProduct("L004", "Anticongelante para Moto", "https://example.com/antifreeze-moto.jpg", 70, BigDecimal.valueOf(9.25), BigDecimal.valueOf(18.50), true, 3L));
        products.add(this.generateProduct("L005", "Lubricante para Cadena", "https://example.com/chain-lubricant.jpg", 90, BigDecimal.valueOf(5.00), BigDecimal.valueOf(10.00), true, 3L));
        products.add(this.generateProduct("L006", "Aceite Sintético 5W-30", "https://example.com/synthetic-oil.jpg", 60, BigDecimal.valueOf(14.00), BigDecimal.valueOf(28.00), true, 3L));
        products.add(this.generateProduct("L007", "Lubricante para Cable de Acelerador", "https://example.com/throttle-cable-lube.jpg", 45, BigDecimal.valueOf(6.00), BigDecimal.valueOf(12.00), true, 3L));
        products.add(this.generateProduct("L008", "Fluido de Frenos DOT 5.1", "https://example.com/brake-fluid-dot5.jpg", 30, BigDecimal.valueOf(8.50), BigDecimal.valueOf(17.00), true, 3L));
        products.add(this.generateProduct("L009", "Aceite para Amortiguadores", "https://example.com/shock-oil.jpg", 25, BigDecimal.valueOf(16.00), BigDecimal.valueOf(32.00), true, 3L));
        products.add(this.generateProduct("L010", "Lubricante Seco para Cerraduras", "https://example.com/dry-lock-lube.jpg", 50, BigDecimal.valueOf(5.50), BigDecimal.valueOf(11.00), true, 3L));
        products.add(this.generateProduct("L011", "Aceite para Transmisión 75W-90", "https://example.com/gear-oil.jpg", 40, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 3L));
        products.add(this.generateProduct("L012", "Protector de Gomas y Juntas", "https://example.com/rubber-protector.jpg", 35, BigDecimal.valueOf(9.00), BigDecimal.valueOf(18.00), true, 3L));
        products.add(this.generateProduct("L013", "Limpiador de Frenos en Aerosol", "https://example.com/brake-cleaner.jpg", 60, BigDecimal.valueOf(7.50), BigDecimal.valueOf(15.00), true, 3L));
        products.add(this.generateProduct("L014", "Aceite para Cadenas con Cera", "https://example.com/wax-chain-lube.jpg", 55, BigDecimal.valueOf(8.00), BigDecimal.valueOf(16.00), true, 3L));
        products.add(this.generateProduct("L015", "Lubricante para Rodamientos", "https://example.com/bearing-grease.jpg", 30, BigDecimal.valueOf(10.00), BigDecimal.valueOf(20.00), true, 3L));
        products.add(this.generateProduct("L016", "Aditivo para Combustible", "https://example.com/fuel-additive.jpg", 70, BigDecimal.valueOf(6.50), BigDecimal.valueOf(13.00), true, 3L));
        products.add(this.generateProduct("L017", "Aceite para Motor 2T", "https://example.com/2t-oil.jpg", 40, BigDecimal.valueOf(11.00), BigDecimal.valueOf(22.00), true, 3L));
        products.add(this.generateProduct("L018", "Líquido Refrigerante Concentrado", "https://example.com/coolant-concentrate.jpg", 25, BigDecimal.valueOf(15.00), BigDecimal.valueOf(30.00), true, 3L));
        products.add(this.generateProduct("L019", "Lubricante para Pistones", "https://example.com/piston-lube.jpg", 20, BigDecimal.valueOf(12.50), BigDecimal.valueOf(25.00), true, 3L));
        products.add(this.generateProduct("L020", "Aceite para Hidráulico de Suspensión", "https://example.com/suspension-fluid.jpg", 15, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 3L));

        // Frenos y Embragues
        products.add(this.generateProduct("F001", "Pastillas de Freno Delantero", "https://example.com/brake-pads-moto.jpg", 60, BigDecimal.valueOf(15.00), BigDecimal.valueOf(30.00), true, 4L));
        products.add(this.generateProduct("F002", "Disco de Freno Trasero", "https://example.com/rear-brake-disc.jpg", 30, BigDecimal.valueOf(40.00), BigDecimal.valueOf(75.00), true, 4L));
        products.add(this.generateProduct("F003", "Kit de Embrague para Moto", "https://example.com/clutch-kit-moto.jpg", 25, BigDecimal.valueOf(55.00), BigDecimal.valueOf(110.00), true, 4L));
        products.add(this.generateProduct("F004", "Cable de Embrague", "https://example.com/clutch-cable.jpg", 35, BigDecimal.valueOf(8.00), BigDecimal.valueOf(16.00), true, 4L));
        products.add(this.generateProduct("F005", "Bomba de Freno Trasero", "https://example.com/rear-brake-pump.jpg", 10, BigDecimal.valueOf(50.00), BigDecimal.valueOf(95.00), true, 4L));
        products.add(this.generateProduct("F006", "Pastillas de Freno Orgánicas", "https://example.com/organic-brake-pads.jpg", 40, BigDecimal.valueOf(16.00), BigDecimal.valueOf(32.00), true, 4L));
        products.add(this.generateProduct("F007", "Pastillas de Freno Sinterizadas", "https://example.com/sintered-brake-pads.jpg", 30, BigDecimal.valueOf(20.00), BigDecimal.valueOf(40.00), true, 4L));
        products.add(this.generateProduct("F008", "Líquido de Frenos Racing DOT 4", "https://example.com/racing-brake-fluid.jpg", 25, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 4L));
        products.add(this.generateProduct("F009", "Kit de Reconstrucción de Bomba de Freno", "https://example.com/brake-pump-rebuild.jpg", 15, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 4L));
        products.add(this.generateProduct("F010", "Sensor de Desgaste de Pastillas", "https://example.com/brake-wear-sensor.jpg", 50, BigDecimal.valueOf(8.00), BigDecimal.valueOf(16.00), true, 4L));
        products.add(this.generateProduct("F011", "Manguera de Freno Trenzada", "https://example.com/braided-brake-line.jpg", 20, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 4L));
        products.add(this.generateProduct("F012", "Plato de Embrague", "https://example.com/clutch-plate.jpg", 25, BigDecimal.valueOf(28.00), BigDecimal.valueOf(56.00), true, 4L));
        products.add(this.generateProduct("F013", "Resorte de Embrague", "https://example.com/clutch-spring.jpg", 40, BigDecimal.valueOf(6.50), BigDecimal.valueOf(13.00), true, 4L));
        products.add(this.generateProduct("F014", "Rodamiento de Empuje de Embrague", "https://example.com/clutch-bearing.jpg", 30, BigDecimal.valueOf(14.00), BigDecimal.valueOf(28.00), true, 4L));
        products.add(this.generateProduct("F015", "Cable de Freno Trasero", "https://example.com/rear-brake-cable.jpg", 35, BigDecimal.valueOf(9.00), BigDecimal.valueOf(18.00), true, 4L));
        products.add(this.generateProduct("F016", "Disco de Freno Wave", "https://example.com/wave-brake-disc.jpg", 15, BigDecimal.valueOf(65.00), BigDecimal.valueOf(130.00), true, 4L));
        products.add(this.generateProduct("F017", "Kit de Juntas para Bomba de Freno", "https://example.com/brake-pump-gasket.jpg", 25, BigDecimal.valueOf(11.00), BigDecimal.valueOf(22.00), true, 4L));
        products.add(this.generateProduct("F018", "Sensor ABS para Moto", "https://example.com/abs-sensor.jpg", 10, BigDecimal.valueOf(45.00), BigDecimal.valueOf(90.00), true, 4L));
        products.add(this.generateProduct("F019", "Guía de Pastillas de Freno", "https://example.com/brake-pad-guide.jpg", 50, BigDecimal.valueOf(5.00), BigDecimal.valueOf(10.00), true, 4L));
        products.add(this.generateProduct("F020", "Tornillos de Freno Avellanados", "https://example.com/brake-screws.jpg", 100, BigDecimal.valueOf(2.50), BigDecimal.valueOf(5.00), true, 4L));

        // Suspensión y Dirección
        products.add(this.generateProduct("S001", "Amortiguador Trasero", "https://example.com/rear-shock-absorber.jpg", 20, BigDecimal.valueOf(65.00), BigDecimal.valueOf(130.00), true, 5L));
        products.add(this.generateProduct("S002", "Kit de Rodamientos de Dirección", "https://example.com/steering-bearings.jpg", 15, BigDecimal.valueOf(25.00), BigDecimal.valueOf(50.00), true, 5L));
        products.add(this.generateProduct("S003", "Horquilla Delantera", "https://example.com/front-fork.jpg", 10, BigDecimal.valueOf(90.00), BigDecimal.valueOf(180.00), true, 5L));
        products.add(this.generateProduct("S004", "Barra de Dirección", "https://example.com/handlebar.jpg", 30, BigDecimal.valueOf(20.00), BigDecimal.valueOf(40.00), true, 5L));
        products.add(this.generateProduct("S005", "Kit de Retenes de Suspensión", "https://example.com/suspension-seals.jpg", 25, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 5L));
        products.add(this.generateProduct("S006", "Kit de Retenes para Horquilla", "https://example.com/fork-seal-kit.jpg", 25, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 5L));
        products.add(this.generateProduct("S007", "Aceite para Horquilla 10W", "https://example.com/fork-oil.jpg", 30, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 5L));
        products.add(this.generateProduct("S008", "Barras Estabilizadoras", "https://example.com/stabilizer-bars.jpg", 15, BigDecimal.valueOf(55.00), BigDecimal.valueOf(110.00), true, 5L));
        products.add(this.generateProduct("S009", "Rodamiento de Rueda Delantera", "https://example.com/front-wheel-bearing.jpg", 40, BigDecimal.valueOf(14.00), BigDecimal.valueOf(28.00), true, 5L));
        products.add(this.generateProduct("S010", "Rodamiento de Rueda Trasera", "https://example.com/rear-wheel-bearing.jpg", 40, BigDecimal.valueOf(16.00), BigDecimal.valueOf(32.00), true, 5L));
        products.add(this.generateProduct("S011", "Kit de Reconstrucción de Amortiguador", "https://example.com/shock-rebuild-kit.jpg", 20, BigDecimal.valueOf(32.00), BigDecimal.valueOf(64.00), true, 5L));
        products.add(this.generateProduct("S012", "Resorte de Suspensión Trasera", "https://example.com/rear-spring.jpg", 15, BigDecimal.valueOf(45.00), BigDecimal.valueOf(90.00), true, 5L));
        products.add(this.generateProduct("S013", "Junta Tórica para Suspensión", "https://example.com/suspension-o-ring.jpg", 60, BigDecimal.valueOf(3.00), BigDecimal.valueOf(6.00), true, 5L));
        products.add(this.generateProduct("S014", "Soporte Inferior de Horquilla", "https://example.com/fork-lower.jpg", 10, BigDecimal.valueOf(75.00), BigDecimal.valueOf(150.00), true, 5L));
        products.add(this.generateProduct("S015", "Kit de Rodamientos de Dirección", "https://example.com/steering-bearing-kit.jpg", 25, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 5L));
        products.add(this.generateProduct("S016", "Protector de Horquilla", "https://example.com/fork-protector.jpg", 30, BigDecimal.valueOf(15.00), BigDecimal.valueOf(30.00), true, 5L));
        products.add(this.generateProduct("S017", "Perno de Dirección", "https://example.com/steering-stem.jpg", 12, BigDecimal.valueOf(28.00), BigDecimal.valueOf(56.00), true, 5L));
        products.add(this.generateProduct("S018", "Junta de Basculante", "https://example.com/swingarm-gasket.jpg", 25, BigDecimal.valueOf(8.00), BigDecimal.valueOf(16.00), true, 5L));
        products.add(this.generateProduct("S019", "Kit de Cojinetes de Basculante", "https://example.com/swingarm-bearings.jpg", 20, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 5L));
        products.add(this.generateProduct("S020", "Tensor de Cadena Ajustable", "https://example.com/adjustable-chain-tensioner.jpg", 15, BigDecimal.valueOf(25.00), BigDecimal.valueOf(50.00), true, 5L));

        // Accesorios y Personalización
        products.add(this.generateProduct("A001", "Espejos Retrovisores Deportivos", "https://example.com/sport-mirrors.jpg", 40, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 6L));
        products.add(this.generateProduct("A002", "Manillar Cromado", "https://example.com/chrome-handlebar.jpg", 20, BigDecimal.valueOf(35.00), BigDecimal.valueOf(70.00), true, 6L));
        products.add(this.generateProduct("A003", "Sillín Deportivo", "https://example.com/sport-seat.jpg", 15, BigDecimal.valueOf(50.00), BigDecimal.valueOf(100.00), true, 6L));
        products.add(this.generateProduct("A004", "Luces LED para Moto", "https://example.com/led-lights-moto.jpg", 50, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 6L));
        products.add(this.generateProduct("A005", "Portamatrículas Personalizado", "https://example.com/custom-license-plate.jpg", 30, BigDecimal.valueOf(10.00), BigDecimal.valueOf(20.00), true, 6L));
        products.add(this.generateProduct("A006", "Pegatina Kit Personalizado", "https://example.com/custom-sticker-kit.jpg", 50, BigDecimal.valueOf(15.00), BigDecimal.valueOf(30.00), true, 6L));
        products.add(this.generateProduct("A007", "Protector de Manillar", "https://example.com/handlebar-protector.jpg", 30, BigDecimal.valueOf(12.00), BigDecimal.valueOf(24.00), true, 6L));
        products.add(this.generateProduct("A008", "Porta Teléfono para Moto", "https://example.com/phone-mount.jpg", 40, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 6L));
        products.add(this.generateProduct("A009", "Cubre Cadena Decorativo", "https://example.com/chain-cover.jpg", 25, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 6L));
        products.add(this.generateProduct("A010", "Espejos Retrovisores Azules", "https://example.com/blue-mirrors.jpg", 20, BigDecimal.valueOf(28.00), BigDecimal.valueOf(56.00), true, 6L));
        products.add(this.generateProduct("A011", "Pedaleras Deportivas", "https://example.com/sport-footpegs.jpg", 30, BigDecimal.valueOf(35.00), BigDecimal.valueOf(70.00), true, 6L));
        products.add(this.generateProduct("A012", "Tapa de Depósito de Combustible", "https://example.com/fuel-cap.jpg", 40, BigDecimal.valueOf(16.00), BigDecimal.valueOf(32.00), true, 6L));
        products.add(this.generateProduct("A013", "Sistema de Iluminación LED", "https://example.com/full-led-kit.jpg", 15, BigDecimal.valueOf(65.00), BigDecimal.valueOf(130.00), true, 6L));
        products.add(this.generateProduct("A014", "Portamatrícula LED", "https://example.com/led-license-plate.jpg", 25, BigDecimal.valueOf(22.00), BigDecimal.valueOf(44.00), true, 6L));
        products.add(this.generateProduct("A015", "Cubre Escape", "https://example.com/exhaust-cover.jpg", 20, BigDecimal.valueOf(18.00), BigDecimal.valueOf(36.00), true, 6L));
        products.add(this.generateProduct("A016", "Asiento con Calefacción", "https://example.com/heated-seat.jpg", 10, BigDecimal.valueOf(85.00), BigDecimal.valueOf(170.00), true, 6L));
        products.add(this.generateProduct("A017", "Kit de Limpieza Premium", "https://example.com/premium-cleaner-kit.jpg", 35, BigDecimal.valueOf(25.00), BigDecimal.valueOf(50.00), true, 6L));
        products.add(this.generateProduct("A018", "Cubre Llantas Reflectante", "https://example.com/rim-tape.jpg", 50, BigDecimal.valueOf(8.00), BigDecimal.valueOf(16.00), true, 6L));
        products.add(this.generateProduct("A019", "Soporte para GPS", "https://example.com/gps-mount.jpg", 25, BigDecimal.valueOf(28.00), BigDecimal.valueOf(56.00), true, 6L));
        products.add(this.generateProduct("A020", "Alarma para Moto con Inmovilizador", "https://example.com/moto-alarm.jpg", 15, BigDecimal.valueOf(55.00), BigDecimal.valueOf(110.00), true, 6L));

        return products;
    }

    public List<CategoryEntity> categories() {
        List<CategoryEntity> categories = new ArrayList<>();

        categories.add(this.generateCategory("Repuestos de Motor", "Repuestos y piezas para motores de motocicletas", "https://example.com/motor-parts.jpg"));
        categories.add(this.generateCategory("Herramientas Manuales", "Herramientas manuales para reparaciones de motos", "https://example.com/hand-tools.jpg"));
        categories.add(this.generateCategory("Lubricantes y Fluidos", "Aceites, lubricantes y fluidos para motocicletas", "https://example.com/lubricants.jpg"));
        categories.add(this.generateCategory("Frenos y Embragues", "Componentes para sistemas de frenos y embragues de motos", "https://example.com/brakes-clutches.jpg"));
        categories.add(this.generateCategory("Suspensión y Dirección", "Piezas para sistemas de suspensión y dirección de motos", "https://example.com/suspension-steering.jpg"));
        categories.add(this.generateCategory("Accesorios y Personalización", "Accesorios para personalizar y mejorar motocicletas", "https://example.com/moto-accessories.jpg"));

        return categories;
    }

    public List<PurchasesEntity> purchases() {
        List<PurchasesEntity> purchases = new ArrayList<>();

        // Note: Your generatePurchase method doesn't take 'count' or specific dates,
        // so those details from SQL are omitted here to fit the current method signature.
        // If 'count' and dates are needed in PurchasesEntity, you'll need to update generatePurchase method and PurchasesEntity accordingly.

        purchases.add(this.generatePurchase("Coca-Cola", BigDecimal.valueOf(0.1), BigDecimal.valueOf(450.75)));
        purchases.add(this.generatePurchase("Nestlé", BigDecimal.valueOf(0.05), BigDecimal.valueOf(120.00)));
        purchases.add(this.generatePurchase("Unilever", BigDecimal.valueOf(0.15), BigDecimal.valueOf(700.50)));
        purchases.add(this.generatePurchase("PepsiCo", BigDecimal.valueOf(0.2), BigDecimal.valueOf(300.00)));
        purchases.add(this.generatePurchase("Procter & Gamble", BigDecimal.valueOf(0.12), BigDecimal.valueOf(250.25)));
        purchases.add(this.generatePurchase("Microsoft", BigDecimal.valueOf(0.03), BigDecimal.valueOf(1200.00)));
        purchases.add(this.generatePurchase("Apple", BigDecimal.valueOf(0.01), BigDecimal.valueOf(1500.00)));
        purchases.add(this.generatePurchase("Samsung", BigDecimal.valueOf(0.07), BigDecimal.valueOf(800.00)));
        purchases.add(this.generatePurchase("Sony", BigDecimal.valueOf(0.18), BigDecimal.valueOf(400.00)));
        purchases.add(this.generatePurchase("LG", BigDecimal.valueOf(0.25), BigDecimal.valueOf(600.00)));
        purchases.add(this.generatePurchase("Adidas", BigDecimal.valueOf(0.09), BigDecimal.valueOf(350.00)));
        purchases.add(this.generatePurchase("Nike", BigDecimal.valueOf(0.11), BigDecimal.valueOf(550.00)));
        purchases.add(this.generatePurchase("Dell", BigDecimal.valueOf(0.06), BigDecimal.valueOf(900.00)));
        purchases.add(this.generatePurchase("HP", BigDecimal.valueOf(0.13), BigDecimal.valueOf(750.00)));
        purchases.add(this.generatePurchase("Lenovo", BigDecimal.valueOf(0.17), BigDecimal.valueOf(1100.00)));
        purchases.add(this.generatePurchase("Amazon", BigDecimal.valueOf(0.3), BigDecimal.valueOf(1500.00)));
        purchases.add(this.generatePurchase("Google", BigDecimal.valueOf(0.02), BigDecimal.valueOf(2000.00)));
        purchases.add(this.generatePurchase("Meta", BigDecimal.valueOf(0.04), BigDecimal.valueOf(1800.00)));
        purchases.add(this.generatePurchase("Netflix", BigDecimal.valueOf(0.08), BigDecimal.valueOf(250.00)));
        purchases.add(this.generatePurchase("Disney", BigDecimal.valueOf(0.1), BigDecimal.valueOf(400.00)));
        purchases.add(this.generatePurchase("Dell", BigDecimal.valueOf(0.10), BigDecimal.valueOf(850.00)));
        purchases.add(this.generatePurchase("HP", BigDecimal.valueOf(0.05), BigDecimal.valueOf(600.00)));
        purchases.add(this.generatePurchase("Lenovo", BigDecimal.valueOf(0.12), BigDecimal.valueOf(1100.00)));
        purchases.add(this.generatePurchase("Asus", BigDecimal.valueOf(0.08), BigDecimal.valueOf(720.00)));
        purchases.add(this.generatePurchase("Acer", BigDecimal.valueOf(0.07), BigDecimal.valueOf(550.00)));
        purchases.add(this.generatePurchase("MSI", BigDecimal.valueOf(0.15), BigDecimal.valueOf(950.00)));
        purchases.add(this.generatePurchase("Razer", BigDecimal.valueOf(0.20), BigDecimal.valueOf(1200.00)));
        purchases.add(this.generatePurchase("Logitech", BigDecimal.valueOf(0.03), BigDecimal.valueOf(300.00)));
        purchases.add(this.generatePurchase("Corsair", BigDecimal.valueOf(0.10), BigDecimal.valueOf(450.00)));
        purchases.add(this.generatePurchase("HyperX", BigDecimal.valueOf(0.06), BigDecimal.valueOf(280.00)));
        purchases.add(this.generatePurchase("Seagate", BigDecimal.valueOf(0.09), BigDecimal.valueOf(150.00)));
        purchases.add(this.generatePurchase("Western Digital", BigDecimal.valueOf(0.04), BigDecimal.valueOf(180.00)));
        purchases.add(this.generatePurchase("Crucial", BigDecimal.valueOf(0.11), BigDecimal.valueOf(100.00)));
        purchases.add(this.generatePurchase("Kingston", BigDecimal.valueOf(0.02), BigDecimal.valueOf(80.00)));
        purchases.add(this.generatePurchase("Samsung", BigDecimal.valueOf(0.08), BigDecimal.valueOf(250.00)));
        purchases.add(this.generatePurchase("Intel", BigDecimal.valueOf(0.15), BigDecimal.valueOf(700.00)));
        purchases.add(this.generatePurchase("AMD", BigDecimal.valueOf(0.12), BigDecimal.valueOf(600.00)));
        purchases.add(this.generatePurchase("NVIDIA", BigDecimal.valueOf(0.25), BigDecimal.valueOf(1500.00)));
        purchases.add(this.generatePurchase("TP-Link", BigDecimal.valueOf(0.05), BigDecimal.valueOf(120.00)));
        purchases.add(this.generatePurchase("Netgear", BigDecimal.valueOf(0.10), BigDecimal.valueOf(200.00)));
        purchases.add(this.generatePurchase("D-Link", BigDecimal.valueOf(0.07), BigDecimal.valueOf(150.00)));
        purchases.add(this.generatePurchase("Cisco", BigDecimal.valueOf(0.30), BigDecimal.valueOf(2000.00)));
        purchases.add(this.generatePurchase("Ubiquiti", BigDecimal.valueOf(0.18), BigDecimal.valueOf(500.00)));
        purchases.add(this.generatePurchase("Canon", BigDecimal.valueOf(0.10), BigDecimal.valueOf(300.00)));
        purchases.add(this.generatePurchase("Epson", BigDecimal.valueOf(0.06), BigDecimal.valueOf(250.00)));
        purchases.add(this.generatePurchase("HP Inc.", BigDecimal.valueOf(0.09), BigDecimal.valueOf(400.00)));
        purchases.add(this.generatePurchase("Brother", BigDecimal.valueOf(0.04), BigDecimal.valueOf(180.00)));
        purchases.add(this.generatePurchase("Xerox", BigDecimal.valueOf(0.22), BigDecimal.valueOf(900.00)));
        purchases.add(this.generatePurchase("Lexmark", BigDecimal.valueOf(0.13), BigDecimal.valueOf(350.00)));
        purchases.add(this.generatePurchase("Ricoh", BigDecimal.valueOf(0.17), BigDecimal.valueOf(600.00)));
        purchases.add(this.generatePurchase("Panasonic", BigDecimal.valueOf(0.08), BigDecimal.valueOf(280.00)));
        purchases.add(this.generatePurchase("Sony", BigDecimal.valueOf(0.11), BigDecimal.valueOf(500.00)));
        purchases.add(this.generatePurchase("LG", BigDecimal.valueOf(0.06), BigDecimal.valueOf(400.00)));
        purchases.add(this.generatePurchase("Philips", BigDecimal.valueOf(0.03), BigDecimal.valueOf(150.00)));
        purchases.add(this.generatePurchase("Bose", BigDecimal.valueOf(0.19), BigDecimal.valueOf(700.00)));
        purchases.add(this.generatePurchase("JBL", BigDecimal.valueOf(0.07), BigDecimal.valueOf(250.00)));
        purchases.add(this.generatePurchase("Harman Kardon", BigDecimal.valueOf(0.14), BigDecimal.valueOf(600.00)));
        purchases.add(this.generatePurchase("Sennheiser", BigDecimal.valueOf(0.16), BigDecimal.valueOf(450.00)));
        purchases.add(this.generatePurchase("Audio-Technica", BigDecimal.valueOf(0.09), BigDecimal.valueOf(320.00)));
        purchases.add(this.generatePurchase("Shure", BigDecimal.valueOf(0.12), BigDecimal.valueOf(550.00)));
        purchases.add(this.generatePurchase("Blue Microphones", BigDecimal.valueOf(0.20), BigDecimal.valueOf(380.00)));
        purchases.add(this.generatePurchase("Rode", BigDecimal.valueOf(0.13), BigDecimal.valueOf(420.00)));
        purchases.add(this.generatePurchase("Zoom", BigDecimal.valueOf(0.08), BigDecimal.valueOf(200.00)));
        purchases.add(this.generatePurchase("GoPro", BigDecimal.valueOf(0.25), BigDecimal.valueOf(300.00)));
        purchases.add(this.generatePurchase("DJI", BigDecimal.valueOf(0.30), BigDecimal.valueOf(800.00)));
        purchases.add(this.generatePurchase("Garmin", BigDecimal.valueOf(0.15), BigDecimal.valueOf(350.00)));
        purchases.add(this.generatePurchase("Fitbit", BigDecimal.valueOf(0.10), BigDecimal.valueOf(180.00)));
        purchases.add(this.generatePurchase("Polar", BigDecimal.valueOf(0.12), BigDecimal.valueOf(220.00)));
        purchases.add(this.generatePurchase("Xiaomi", BigDecimal.valueOf(0.05), BigDecimal.valueOf(100.00)));
        purchases.add(this.generatePurchase("OnePlus", BigDecimal.valueOf(0.07), BigDecimal.valueOf(600.00)));
        purchases.add(this.generatePurchase("Apple", BigDecimal.valueOf(0.10), BigDecimal.valueOf(1500.00)));
        purchases.add(this.generatePurchase("Google", BigDecimal.valueOf(0.05), BigDecimal.valueOf(1200.00)));
        purchases.add(this.generatePurchase("Microsoft", BigDecimal.valueOf(0.08), BigDecimal.valueOf(900.00)));
        purchases.add(this.generatePurchase("Amazon", BigDecimal.valueOf(0.12), BigDecimal.valueOf(750.00)));
        purchases.add(this.generatePurchase("Meta", BigDecimal.valueOf(0.15), BigDecimal.valueOf(2000.00)));

        return purchases;
    }

    @Override
    public boolean seedALl() {
        List<CategoryEntity> categories = this.categories();
        categories.forEach(this._categoryRepository::save);
        List<ProductEntity> products = this.products();
        products.forEach(this._productRepository::save);
        List<PurchasesEntity> purchases = this.purchases();
        purchases.forEach(this._purchaseRepository::save);

        return true;
    }
}