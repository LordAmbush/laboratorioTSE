/*
   Copyright 2009-2022 PrimeTek.

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.primefaces.siberia.view;

import org.primefaces.PrimeFaces;
import org.primefaces.siberia.domain.Product;
import org.primefaces.siberia.service.OrderService;
import org.primefaces.siberia.service.ProductService;

import tec.inf.javaEE.lab2023.business.BalanzaBusinessLocal;
import tec.inf.javaEE.lab2023.business.GraficasBusinessLocal;
import tec.inf.javaEE.lab2023.business.TrackingBusinessLocal;
import tec.inf.javaEE.lab2023.dto.InfoViajesDTO;
import tec.inf.javaEE.lab2023.dto.PesadasDTO;
import tec.inf.javaEE.lab2023.dto.TrackingDTO;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@ViewScoped
public class CrudDemoView implements Serializable {
	
	@EJB
	GraficasBusinessLocal graficasService;
	
	@EJB
	BalanzaBusinessLocal balanzaService;
	
	@EJB
	TrackingBusinessLocal trackingBusiness;
    
    private List<Product> products;
    private List<InfoViajesDTO> infoViajes;
    private List<PesadasDTO> infoBalanza;
    private List<TrackingDTO> infoTracking;

    private Product selectedProduct;

    private List<Product> selectedProducts;

    @Inject
    private ProductService productService;

    @Inject
    private OrderService orderService;

    @PostConstruct
    public void init() {
        //this.products = this.productService.getClonedProducts(30);
        this.infoViajes = graficasService.InfoViajes();
        this.infoBalanza = balanzaService.listarPesadas();
        this.setInfoTracking(trackingBusiness.listarTracking());
    }
    
    public List<Product> getProducts() {
        return products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = new Product();
    }

    public void saveProduct() {
        if (this.selectedProduct.getCode() == null) {
            this.selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.products.add(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }
        
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        this.products.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility() == Visibility.VISIBLE) {
            Product product = (Product) event.getData();
            if (product.getOrders() == null) {
                product.setOrders(orderService.getOrders((int) (Math.random() * 10)));
            }
        }
    }

	public List<InfoViajesDTO> getInfoViajes() {
		return infoViajes;
	}

	public void setInfoViajes(List<InfoViajesDTO> infoViajes) {
		this.infoViajes = infoViajes;
	}

	public List<PesadasDTO> getInfoBalanza() {
		return infoBalanza;
	}

	public void setInfoBalanza(List<PesadasDTO> infoBalanza) {
		this.infoBalanza = infoBalanza;
	}

	public List<TrackingDTO> getInfoTracking() {
		return infoTracking;
	}

	public void setInfoTracking(List<TrackingDTO> infoTracking) {
		this.infoTracking = infoTracking;
	}
}
