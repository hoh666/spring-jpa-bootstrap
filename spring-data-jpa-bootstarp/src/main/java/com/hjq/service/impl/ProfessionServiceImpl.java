package com.hjq.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjq.entity.Profession;
import com.hjq.repository.ProfessionRepository;
import com.hjq.service.ProfessionService;

@Service("professionService")
public class ProfessionServiceImpl implements ProfessionService {

	private final static Logger logger = LoggerFactory.getLogger(ProfessionServiceImpl.class);

	@Inject private ProfessionRepository professionRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Profession create(Profession entity) {

		logger.info("save profession entity = {}", ToStringBuilder.reflectionToString(entity, ToStringStyle.SHORT_PREFIX_STYLE));
		return professionRepository.save(entity);
	}

	@Override
	@Transactional
	public Page<Profession> findAll(Profession item, Sort sort) {
		if (item == null) {
			Pageable pageable = new PageRequest(DEFAULT_PAGE, DEFAULT_PAGE_SIZE, sort);
			return professionRepository.findAll(pageable);
		} else {
			return new PageImpl<Profession>(professionRepository.findAll());
		}
	}

	@Override
	@Transactional
	public Profession findOne(Long id) {

		return professionRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Profession deleteById(Long id) {

		Profession profession = professionRepository.findOne(id);
		professionRepository.delete(profession);
		return profession;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Profession update(Profession entity) {

		return professionRepository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteInBatch(List<Profession> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<Profession> saveInBatch(List<Profession> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public long count(Profession entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
