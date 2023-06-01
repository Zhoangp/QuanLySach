package com.example.quanlysach.Repo;

import com.example.quanlysach.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRespository extends JpaRepository<Role, Integer> {
    @Query("SELECT u FROM Role u WHERE u.name = ?1")
    public Role getRoleByName(String name);
}
