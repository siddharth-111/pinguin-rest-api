package com.example.SpringBoot.service.serviceImpl;

import com.example.SpringBoot.model.Instrument;
import com.example.SpringBoot.dao.InstrumentDAO;
import com.example.SpringBoot.exception.BadRequestException;
import com.example.SpringBoot.exception.ResourceNotFoundException;
import com.example.SpringBoot.repository.InstrumentsRepository;
import com.example.SpringBoot.service.serviceInterface.CandlesticksService;
import com.example.SpringBoot.service.serviceInterface.InstrumentsService;
import com.example.SpringBoot.service.serviceInterface.QuotesService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentsServiceImpl implements InstrumentsService {

    Logger logger = LoggerFactory.getLogger(InstrumentsServiceImpl.class);

    @Autowired
    InstrumentsRepository instrumentsRepository;

    @Autowired
    QuotesService quotesService;

    @Autowired
    CandlesticksService candlesticksService;

    @Autowired
    ModelMapper modelMapper;

    public void saveInstrument(Instrument instrument)
    {
        try
        {
            logger.debug("Saving the instrument with isin: " + instrument.getIsin());
            InstrumentDAO instrumentDAO = modelMapper.map(instrument, InstrumentDAO.class);
            instrumentsRepository.save(instrumentDAO);
        }
        catch (Exception e)
        {
            throw new BadRequestException("Error in saving the instrument, error is :" + e);
        }
    }

    public void deleteInstrument(Instrument instrument) throws Exception
    {
        final String isin = instrument.getIsin();
        logger.debug("Deleting the instrument with isin: " + instrument.getIsin());

        instrumentsRepository.findById(isin)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete as there exists no following isin: " + isin));

        instrumentsRepository.deleteById(isin);

        // Delete associated quotes and candlesticks
        quotesService.deleteQuotesByIsinBeforeCreationTime(isin, Date.from(Instant.now()));
        candlesticksService.deleteCandlesticksByIsinBeforeCreationTime(isin, Date.from(Instant.now()));
    }

    public List<Instrument> getInstruments() throws Exception
    {
        List<InstrumentDAO> instrumentDAOS = instrumentsRepository.findAll();

        List<Instrument> instruments = instrumentDAOS.stream().
                map(instrumentDAO -> modelMapper.map(instrumentDAO, Instrument.class)).collect(Collectors.toList());

        return instruments;
    }

}
