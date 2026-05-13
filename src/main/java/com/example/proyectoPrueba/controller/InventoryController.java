// Linea prueba 1

package com.example.proyectoPrueba.controller;

<<<<<<< HEAD
import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import com.example.proyectoPrueba.dto.InventoryDto;
import com.example.proyectoPrueba.model.Inventory;
// import com.example.proyectoPrueba.model.Product; // Lo comento si no lo usas directamente aquí
import com.example.proyectoPrueba.service.InventoryService;
import org.springframework.data.domain.Page; // <-- IMPORTANTE: Añadimos esta importación
>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Linea de prueba jc
@RestController
@RequestMapping
public class InventoryController {

    private final InventoryService inventoryService;

<<<<<<< HEAD
    public InventoryController(ProductService productService){
        this.productService = productService;
    }

    /**
     * operationId: listarProductos
     * GET /productos
     */
    @GetMapping("/productos")
    public ResponseEntity<List<ProductDto>> listarProductos() {
        List<ProductDto> productos = productService.getAll();
        return ResponseEntity.ok(productos);
    }

    /**
     * operationId: buscarProductoPorId
     * GET /productos/{product_id}
     */
    @GetMapping("/productos/{product_id}")
    public ResponseEntity<ProductDto> buscarProductoPorId(
            @PathVariable("product_id") Integer product_id
    ) {
        ProductDto producto = productService.getById(product_id);
        // El GlobalExceptionHandler se encargará de lanzar un 404 si producto es null
        return ResponseEntity.ok(producto);
    }

    /**
     * operationId: agregarProducto
     * POST /agregar
     */
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarProducto(
            @RequestBody Product product
    ) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.OK).body("Producto guardado con éxito");
    }

    /**
     * operationId: borrarProducto
     * DELETE /delete/{product_id}
     */
    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<String> borrarProducto(
            @PathVariable("product_id") Integer product_id
    ) {
        productService.delete(product_id);
        return ResponseEntity.ok("El producto con id: " + product_id + " ha sido eliminado con éxito");
    }
=======
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inv")
    public String create(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
        return "Inventario creado";
    }

    // Tu método original que devuelve TODO de golpe
    @GetMapping("/inv")
    public List<InventoryDto> getAll() {
        return inventoryService.getAll();
    }

    // NUEVO MÉTODO: El que devuelve los datos paginados
    @GetMapping("/inv/paginated")
    public Page<InventoryDto> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return inventoryService.getInventoryPaginated(page, size);
    }

    @GetMapping("/inv/{inventory_id}")
    public InventoryDto getById(
            @PathVariable("inventory_id") Integer inventoryId
    ){
        return inventoryService.getById(inventoryId);
    }

    @DeleteMapping("/inv/{inventory_id}")
    public String deleteInv(
            @PathVariable("inventory_id") Integer inventoryId
    ){
        inventoryService.delete(inventoryId);
        return "Inventario eliminado con éxito";
    }

    @PutMapping("/inv/{inventory_id}")
    public String update(
            @PathVariable("inventory_id")Integer inventoryId,
            @RequestBody Inventory inventory
    ){
        inventoryService.updateInv(inventoryId, inventory);
        return "Los datos del inventario de ID "+inventoryId+" han sido actualizados con éxito";
    }

>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53
}