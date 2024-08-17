package br.edu.ifpb.saac.business.service;

import br.edu.ifpb.saac.model.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

	public enum AVALIABLE_ROLES { ADMIN, USER, STUDENT, EMPLOYEE }

	public void createDefaultValues();

	public Role findByName(String name);

	public Role findDefault();

}