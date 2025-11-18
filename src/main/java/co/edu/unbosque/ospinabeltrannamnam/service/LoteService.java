package co.edu.unbosque.ospinabeltrannamnam.service;

import co.edu.unbosque.ospinabeltrannamnam.dto.LoteDTO;
import co.edu.unbosque.ospinabeltrannamnam.model.Lote;
import co.edu.unbosque.ospinabeltrannamnam.model.OrdenCompra;
import co.edu.unbosque.ospinabeltrannamnam.model.Ingrediente;
import co.edu.unbosque.ospinabeltrannamnam.model.Proveedor;
import co.edu.unbosque.ospinabeltrannamnam.repository.LoteRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.OrdenCompraRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.IngredienteRepository;
import co.edu.unbosque.ospinabeltrannamnam.repository.ProveedorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoteService {

	@Autowired
	private LoteRepository repo;

	@Autowired
	private OrdenCompraRepository ordenRepo;

	@Autowired
	private IngredienteRepository ingredienteRepo;

	@Autowired
	private ProveedorRepository proveedorRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(LoteDTO data) {
		Lote l = new Lote();
		l.setFechaRecepcion(data.getFechaRecepcion());
		l.setFechaCaducidad(data.getFechaCaducidad());
		l.setCantidad(data.getCantidad());
		l.setPrecio(data.getPrecio());

		if (data.getOrdenId() != null) {
			OrdenCompra o = ordenRepo.findById(data.getOrdenId()).orElse(null);
			l.setOrdenCompra(o);
		}
		Ingrediente ingredienteEntity = null;
		if (data.getCodigoIngrediente() != null) {
			ingredienteEntity = ingredienteRepo.findById(data.getCodigoIngrediente()).orElse(null);
			l.setIngrediente(ingredienteEntity);
		}
		if (data.getProveedorId() != null) {
			Proveedor p = proveedorRepo.findById(data.getProveedorId()).orElse(null);
			l.setProveedor(p);
		}
		repo.save(l);

		if (ingredienteEntity != null && data.getPrecio() != null && data.getCantidad() != null
				&& data.getCantidad() > 0) {
			try {
				double div = (double) data.getPrecio() / (double) data.getCantidad();
				int nuevoCosto = (int) Math.round(div);
				ingredienteEntity.setCostoUnitario(nuevoCosto);
				ingredienteRepo.save(ingredienteEntity);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return 0;
	}

	public List<LoteDTO> getAll() {
		return repo.findAll().stream().map(l -> {
			LoteDTO dto = modelMapper.map(l, LoteDTO.class);
			dto.setOrdenId(l.getOrdenCompra() != null ? l.getOrdenCompra().getOrdenId() : null);
			dto.setCodigoIngrediente(l.getIngrediente() != null ? l.getIngrediente().getCodigo() : null);
			dto.setProveedorId(l.getProveedor() != null ? l.getProveedor().getProveedorId() : null);
			dto.setPrecio(l.getPrecio());
			dto.setFechaCaducidad(l.getFechaCaducidad());
			return dto;
		}).collect(Collectors.toList());
	}

	public LoteDTO getById(Integer id) {
		return repo.findById(id).map(l -> {
			LoteDTO dto = modelMapper.map(l, LoteDTO.class);
			dto.setOrdenId(l.getOrdenCompra() != null ? l.getOrdenCompra().getOrdenId() : null);
			dto.setCodigoIngrediente(l.getIngrediente() != null ? l.getIngrediente().getCodigo() : null);
			dto.setProveedorId(l.getProveedor() != null ? l.getProveedor().getProveedorId() : null);
			dto.setPrecio(l.getPrecio());
			dto.setFechaCaducidad(l.getFechaCaducidad());
			return dto;
		}).orElse(null);
	}

	public int updateById(Integer id, LoteDTO newData) {
		Optional<Lote> opt = repo.findById(id);
		if (opt.isEmpty())
			return 1;
		Lote l = opt.get();
		if (newData.getFechaRecepcion() != null)
			l.setFechaRecepcion(newData.getFechaRecepcion());
		if (newData.getFechaCaducidad() != null)
			l.setFechaCaducidad(newData.getFechaCaducidad());
		if (newData.getCantidad() != null)
			l.setCantidad(newData.getCantidad());
		if (newData.getPrecio() != null)
			l.setPrecio(newData.getPrecio());

		if (newData.getOrdenId() != null) {
			OrdenCompra o = ordenRepo.findById(newData.getOrdenId()).orElse(null);
			l.setOrdenCompra(o);
		}
		if (newData.getCodigoIngrediente() != null) {
			Ingrediente i = ingredienteRepo.findById(newData.getCodigoIngrediente()).orElse(null);
			l.setIngrediente(i);
		}
		if (newData.getProveedorId() != null) {
			Proveedor p = proveedorRepo.findById(newData.getProveedorId()).orElse(null);
			l.setProveedor(p);
		}
		repo.save(l);

		try {
			if (l.getIngrediente() != null && l.getPrecio() != null && l.getCantidad() != null && l.getCantidad() > 0) {
				double div = (double) l.getPrecio() / (double) l.getCantidad();
				int nuevoCosto = (int) Math.round(div);
				Ingrediente ing = l.getIngrediente();
				ing.setCostoUnitario(nuevoCosto);
				ingredienteRepo.save(ing);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return 0;
	}

	public int deleteById(Integer id) {
		return repo.findById(id).map(l -> {
			repo.delete(l);
			return 0;
		}).orElse(1);
	}

	public boolean exist(Integer id) {
		return repo.existsById(id);
	}

	public long count() {
		return repo.count();
	}
}
